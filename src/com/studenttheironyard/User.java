package com.studenttheironyard;

import java.io.File;
import java.util.Scanner;

/**
 * Created by hoseasandstrom on 6/8/16.
 */
public class User {
    int id;
    String firstName;
    String lastName;
    String email;
    String country;
    String ipAddress;


    public User(int id, String firstName, String lastName, String email, String country, String ipAddress) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
        this.ipAddress = ipAddress;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                '}';
    }
}
