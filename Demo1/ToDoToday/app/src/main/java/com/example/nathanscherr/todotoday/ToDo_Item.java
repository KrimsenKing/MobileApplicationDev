package com.example.nathanscherr.todotoday;

/**
 * Created by nathanscherr on 2017-03-05.
 */

public class ToDo_Item {

    //Member Attributes
    private int _id;
    private String description;
    private int is_done;

    public ToDo_Item(){}

    public ToDo_Item(int id, String desc, int done){
        setId(id);
        setDescription(desc);
        setIsDone(done);
    }


    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIsDone() {
        return is_done;
    }

    public void setIsDone(int is_done) {
        this.is_done = is_done;
    }
}
