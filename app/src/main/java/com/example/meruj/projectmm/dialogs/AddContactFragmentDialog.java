package com.example.meruj.projectmm.dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.meruj.projectmm.R;

/**
 * Created by Meruj on 2/14/2018.
 */

public class AddContactFragmentDialog extends DialogFragment {
    private EditText name;
    private EditText phoneNumber;
    private Button button;

    public OnAddClikListener getOnAddClikListener() {
        return onAddClikListener;
    }

    public void setOnAddClikListener(OnAddClikListener onAddClikListener) {
        this.onAddClikListener = onAddClikListener;
    }

    private OnAddClikListener onAddClikListener;

    public static AddContactFragmentDialog newInstance(String userName) {

        Bundle args = new Bundle();
        args.putString("User name", userName);

        AddContactFragmentDialog fragment = new AddContactFragmentDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.add_contact_layout, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        name = view.findViewById(R.id.input_name);
        phoneNumber = view.findViewById(R.id.input_password);
        button = view.findViewById(R.id.add_contact);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!name.getText().toString().isEmpty() && !phoneNumber.getText().toString().isEmpty()) {
                    onAddClikListener.onAddClick(name.getText().toString(),phoneNumber.getText().toString());
                }
            }
        });


    }

    public interface OnAddClikListener {
        void onAddClick(String name, String number);
    }
}

