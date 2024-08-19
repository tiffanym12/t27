package com.tco.requests;

public class Credential {
    final static String USER = "cs314-db";
    final static String PASSWORD = "eiK5liet1uej";
    final static String URL = "jdbc:mariadb://faure.cs.colostate.edu/cs314";

    static String url() { return URL; }


    //Methods for testing
    public static String getUser(){
        return USER;
    }

    public static String getPassword(){
        return PASSWORD;
    }
}
