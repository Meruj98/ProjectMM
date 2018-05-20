package com.example.meruj.projectmm.contact_utility;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.meruj.projectmm.R;
import com.example.meruj.projectmm.activities.User_Activity;

import java.util.List;

/**
 * Created by Meruj on 2/14/2018.
 */

public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.PersonViewHolder> {

    private Context context;
    private OnDeleteClickListener onDeleteClickListener;


    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView personName;
        TextView personPhoneNumber;
        Button deleteContact;

        PersonViewHolder(final View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            personName = (TextView) itemView.findViewById(R.id.contact_name);
            personPhoneNumber = (TextView) itemView.findViewById(R.id.contact_surname);
            deleteContact = (Button) itemView.findViewById(R.id.delete_button);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), User_Activity.class);
                    intent.putExtra("1",personName.getText().toString());
                    intent.putExtra("3",personPhoneNumber.getText().toString());
                    view.getContext().startActivity(intent);
                }
            });
        }
    }

    List<Contact> contacts;

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public ContactsListAdapter(List<Contact> contacts, Context context, OnDeleteClickListener onDeleteClickListener) {
        this.onDeleteClickListener = onDeleteClickListener;
        this.contacts = contacts;
        this.context = context;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, final int i) {
        personViewHolder.personName.setText(contacts.get(i).getName());
        personViewHolder.personPhoneNumber.setText(contacts.get(i).getPhoneNumber());
        personViewHolder.deleteContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDeleteClickListener.onDeleteClick(contacts.get(i).getName());
            }
        });


//        personViewHolder.personPhoto.setImageResource(contacts.get(i).photoId);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(String personname);
    }
}
