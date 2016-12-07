package com.example.scherr3143.chapter2_demo5;

/**
 * Created by scherr3143 on 12/7/2016.
 */
public class Painting {

    private String mDescription;
    private int mId;

    public Painting(String description, int id)
    {
        mDescription = description;
        mId = id;
    }
    public String getDescription()
    {
        return mDescription;
    }
    public void setDescription(String description) {
        mDescription = description;
    }
    public int getId() {
        return mId;
    }
    public void setId(int id) {
        mId = id;
    }
}
