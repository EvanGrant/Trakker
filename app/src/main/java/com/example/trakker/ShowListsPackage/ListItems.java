package com.example.trakker.ShowListsPackage;


public class ListItems {

    private String listName;
    private int listID;

    public ListItems (String name, int id){

        this.listName = name;
        this.listID = id;

    }

    public String getListName() {
        return listName;
    }

    public void setListName(String name) {
        this.listName = name;
    }

    public int getListID() {
        return listID;
    }

    public void setListID(int id) {
        this.listID = id;
    }

}
