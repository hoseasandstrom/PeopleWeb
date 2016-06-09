package com.studenttheironyard;


import spark.ModelAndView;
import spark.Session;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    //static HashMap<String, String> userMap = new HashMap<>();
    static ArrayList<User> userList = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        readPeopleFile();
        Spark.init();
        Spark.get(
                "/",
                (request, response) -> {
                    int offset = 0;
                    String offsetStr = request.queryParams("offset");
                    if (offsetStr != null) {
                        offset = Integer.valueOf(offsetStr);
                    }

                    ArrayList tempList = new ArrayList<>(userList.subList(offset, offset + 20));

                    HashMap map = new HashMap();
                    map.put("users", tempList);
                    map.put("offsetUp", offset + 20);
                    map.put("offsetDown", offset - 20);
                    return new ModelAndView(map, "home.html");
                },
                new MustacheTemplateEngine()
        );

        Spark.get(
                "/user",
                (request, response) -> {
                    int id = Integer.valueOf(request.queryParams("id"));
                    User p = userList.get(id - 1);
                    return new ModelAndView(p, "home.html");
                },
                new MustacheTemplateEngine()
        );
    }

    public static void readPeopleFile() throws FileNotFoundException {
        File people = new File("people.csv");
        Scanner fileScanner = new Scanner(people);
        fileScanner.nextLine();
        while (fileScanner.hasNext()) {
            String[] columns = fileScanner.nextLine().split(",");
            User user = new User(Integer.valueOf(columns[0]), columns[1], columns[2], columns[3], columns[4], columns[5]);
            userList.add(user);

        }
    }
}
