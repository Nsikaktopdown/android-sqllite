package com.adibta.android_sqllite.model;

/**
 * Created by Nsikak on 10/5/17.
 */

public class Note_Item {

    private String title, description;
    private  int id;



    public Note_Item(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Note_Item() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}