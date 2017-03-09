package com.example.scherr3143.personnel_app;

import android.net.Uri;

/**
 * Created by scherr3143 on 1/5/2017.
 */
public class Contact extends MainActivity{

    private int Id;
    private Uri PhotoURI;
    private String Name;
    private String Address;
    private String Phone;
    private String Email;

    public Contact(int id, String nm, String eml, String addy, String tn, Uri picURI)
    {
        Id = id;
        PhotoURI = picURI;
        Name = nm;
        Address = addy;
        Phone = tn;
        Email = eml;
    }
    public int getID() {
        return Id;
    }
    public Uri getPhotoUri() {
        return PhotoURI;
    }
    public String getName() {
        return Name;
    }
    public String getAddress() {
        return Address;
    }
    public String getPhone() {
        return Phone;
    }
    public String getEmail() {
        return Email;
    }
}
