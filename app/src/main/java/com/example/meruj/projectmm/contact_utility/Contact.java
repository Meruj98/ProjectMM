package com.example.meruj.projectmm.contact_utility;

/**
 * Created by Meruj on 2/14/2018.
 */

public class Contact {

    //private variables
    int _id;
    public String _name;
    public String _phone_number;
    public String _user_name;

    // Empty constructor
    public Contact() {

    }

    // constructor
    public Contact(int id, String name, String _phone_number, String _user_name) {
        this._id = id;
        this._name = name;
        this._phone_number = _phone_number;
        this._user_name = _user_name;
    }

    // constructor
    public Contact(String name, String _phone_number, String _user_name) {
        this._name = name;
        this._phone_number = _phone_number;
        this._user_name = _user_name;
    }

    // getting ID
    public int getID() {
        return this._id;
    }

    // setting id
    public void setID(int id) {
        this._id = id;
    }

    // getting name
    public String getName() {
        return this._name;
    }

    // setting name
    public void setName(String name) {
        this._name = name;
    }

    public String getUserName() {
        return this._user_name;
    }

    // setting name
    public void setUserName(String _user_name) {
        this._user_name = _user_name;
    }

    // getting phone number
    public String getPhoneNumber() {
        return this._phone_number;
    }

    // setting phone number
    public void setPhoneNumber(String phone_number) {
        this._phone_number = phone_number;
    }
}
