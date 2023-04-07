package com.example.trakker;


import android.app.Application;

public class GlobalClass extends Application {

    private static int userID;
    private static String userEmail;
    private static String userFirstName;
    private static String userLastName;

    public void setUserID(int id){
        this.userID = id;
    }

    public int getUserID(){
        return userID;
    }

    public void setUserEmail(String email){
        this.userEmail = email;
    }

    public String getUserEmail(){
        return userEmail;
    }

    public void setUserFirstName(String firstName){
        this.userFirstName = firstName;
    }

    public String getUserFirstName(){
        return userFirstName;
    }

    public void setUserLastName(String lastName){
        this.userLastName = lastName;
    }

    public String getUserLastName(){
        return userLastName;
    }


}
