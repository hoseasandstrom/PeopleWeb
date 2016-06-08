package com.studenttheironyard;

import jodd.json.JsonSerializer;
import spark.ModelAndView;
import spark.Session;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static HashMap<String, ArrayList<User>> users = new HashMap<>();
    //static ArrayList<Message> messages = new ArrayList<>();

    public static void main(String[] args) {
        Spark.init();
        Spark.get(
                "/",
                (request, response) -> {
                    Session session = request.session();
                    String username = session.attribute("username");

                    String idStr = request.queryParams("userId");
                    int userId = -1;
                    if (idStr != null) {
                        userId = Integer.valueOf(idStr);
                    }
                    HashMap m = new HashMap();
                    m.put("username", username);
                    return new ModelAndView(m, "home.html");
                },
                new MustacheTemplateEngine()
        );
    }
    public static HashMap<String, ArrayList<User>> readPeopleFromFile(String fileName) throws FileNotFoundException {
        HashMap<String, ArrayList<User>> userMap = new HashMap<>();
        File f = new File(fileName);
        Scanner fileScanner = new Scanner(f);
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String[] columns = line.split("\\,");
            User user = new User(columns[0], columns[1], columns[2], columns[3], columns[4], Integer.valueOf(columns[5]),Integer.valueOf(columns[6]));
            String peopleId = String.valueOf(user.firstName);
            if (!userMap.containsKey(peopleId)) {
                userMap.put(peopleId, new ArrayList<>());
            }
            userMap.get(peopleId).add(user);
        }
        return userMap;
    }
    public static void loadUser(String peopleId, String userCode ) throws IOException {
        File peopleFile = new File(String.format("%s_people.csv", peopleId));

        FileWriter fw = new FileWriter(peopleFile);
        fw.write(peopleId);
        fw.close();
    }
    public static void saveUser(ArrayList userArrayList, String peopleId) throws IOException {
        File f = new File(String.format("%s_people.json", peopleId));
        JsonSerializer serializer = new JsonSerializer();
        String json = serializer.serialize(userArrayList);
        FileWriter fw = new FileWriter(f);
        fw.write(json);
        fw.close();
    }



}
