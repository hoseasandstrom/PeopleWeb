package com.studenttheironyard;

import java.io.File;
import java.util.Scanner;

/**
 * Created by hoseasandstrom on 6/8/16.
 */
public class User {
    String peopleId;
    String firstName;
    String lastName;
    String email;
    String country;
    int uniqueUserId;
    int userId;

    public User(String peopleId, String firstName, String lastName, String email, String country, int uniqueUserId, int userId) {
        this.peopleId = peopleId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
        this.uniqueUserId = uniqueUserId;
        this.userId = userId;
    }
}
