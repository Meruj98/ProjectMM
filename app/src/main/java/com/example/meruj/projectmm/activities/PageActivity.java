package com.example.meruj.projectmm.activities;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.meruj.projectmm.dialogs.AddContactFragmentDialog;
import com.example.meruj.projectmm.contact_utility.Contact;
import com.example.meruj.projectmm.contact_utility.ContactsListAdapter;
import com.example.meruj.projectmm.database.DbHelper;
import com.example.meruj.projectmm.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PageActivity extends AppCompatActivity {
    DbHelper dbHelper;
    private List<Contact> contacts;
    private List<Contact> searchedContacts;
    private List<Contact> list;
    private RecyclerView rv;

    private SharedPreferences preferences;


    private AddContactFragmentDialog addContactFragmentDialog;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String userName;
    private FloatingActionButton floatingActionButton, fab_search,fab_exit;
    private ContactsListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        rv = findViewById(R.id.rv);

//        relativeLayout = (RelativeLayout) findViewById(R.id.rel_layout);
        floatingActionButton = findViewById(R.id.fab);
        fab_search = findViewById(R.id.fab_search);
        dbHelper = new DbHelper(this);
        fab_exit = findViewById(R.id.fab_exit);
        preferences = getSharedPreferences("com.example.meruj", MODE_PRIVATE);
        addContactFragmentDialog = AddContactFragmentDialog.newInstance(preferences.getString("LoginUserName", ""));

        addContactFragmentDialog.setOnAddClikListener(new AddContactFragmentDialog.OnAddClikListener() {
            @Override
            public void onAddClick(String name, String number) {
                addContactFragmentDialog.dismiss();
                dbHelper.addContact(new Contact(name, number, preferences.getString("LoginUserName", "")));
                contacts.add(new Contact(name, number, preferences.getString("LoginUserName", "")));
                adapter.notifyDataSetChanged();

            }
        });


        fab_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(PageActivity.this);
                builder.setTitle("SEARCH");
                builder.setMessage("ENTER NAME,PLEASE");
                final EditText input = new EditText(PageActivity.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                builder.setView(input);
                builder.setPositiveButton("Search", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        searchedContacts = new ArrayList<>();
                        filterByName(input.getText().toString(), (ArrayList<Contact>) contacts, (ArrayList<Contact>) searchedContacts);
                        adapter.setContacts(searchedContacts);
                        adapter.notifyDataSetChanged();


                    }
                });
                builder.setNeutralButton("CLEAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        adapter.setContacts(contacts);
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                builder.show();
            }
        });


        initializeData();
        initializeAdapter();

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        fab_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferences.edit().putBoolean("IsLogin", false).apply();
                preferences.edit().putString("LoginUserName", "").apply();
                finish();
            }
        });


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.addToBackStack("fragment1");
                addContactFragmentDialog.show(fragmentTransaction, "tag");
            }
        });

    }

    private void filterByName(String name, ArrayList<Contact> mainArray, ArrayList<Contact> filteredArray) {
        for (Contact contact : mainArray) {
            if (contact.getName().contains(name)) {
                filteredArray.add(contact);
            }
        }
    }

    private void initializeData() {
        SharedPreferences preferences = getSharedPreferences("com.example.meruj", MODE_PRIVATE);
        setUserName(preferences.getString("LoginUserName", ""));

        contacts = dbHelper.getAllContacts(getUserName());


    }

    private void initializeAdapter() {
        adapter = new ContactsListAdapter(contacts, this, new ContactsListAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(final String personName) {
                AlertDialog alertDialog = new AlertDialog.Builder(PageActivity.this).create();
                alertDialog.setTitle("Delete contact");
                alertDialog.setMessage("Do you really want to delete this contact ?");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "DELETE",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                deleteContactByName(personName);
                                dialog.dismiss();
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });
        rv.setAdapter(adapter);
    }

    private void deleteContactByName(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equals(name)) {
                contacts.remove(contacts.indexOf(contact));
                dbHelper.deleteContact(contact);
                adapter.notifyDataSetChanged();
                break;
            }
        }
    }
}
