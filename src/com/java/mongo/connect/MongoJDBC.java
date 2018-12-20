package com.java.mongo.connect;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;


import java.util.ArrayList;
import java.util.List;


public class MongoJDBC {
    public static MongoDatabase mongoDatabase = null;

    public static MongoDatabase connectToMongoDB() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        mongoDatabase = mongoClient.getDatabase("Java");
        System.out.println("Database Connected");
        //System.out.println(mongoDatabase);

        return mongoDatabase;
    }

    public static String insertIntoToMongoDB(User user) {
        String profile = user.getStName();
        MongoDatabase mongoDatabase = connectToMongoDB();
        MongoCollection<Document> collection = mongoDatabase.getCollection("Student_Info");
        Document document = new Document().append("stName", user.getStName()).append("stID", user.getStID()).
                append("stDOB", user.getStDOB());
        collection.insertOne(document);
        return profile + " has been registered";


    }

    public static String deletefromMongoDB(User user) {
        String profile = user.getStName();
        MongoDatabase mongoDatabase = connectToMongoDB();
        MongoCollection<Document> collection = mongoDatabase.getCollection("Student_Info");

        collection.deleteOne(Filters.eq("stName", "Adam"));
        System.out.println(profile + " has been removed");

        return profile;

    }


    public static List<User> readUserProfile() {
        List<User> list = new ArrayList<User>();
        User user = new User();
        MongoDatabase mongoDatabase = connectToMongoDB();
        MongoCollection<Document> collection = mongoDatabase.getCollection("Student_Info");
        BasicDBObject basicDBObject = new BasicDBObject();
        FindIterable<Document> iterable = collection.find(basicDBObject);
        for (Document doc : iterable) {
            String stName = (String) doc.get("stName");
            user.setStName(stName);
            String stID = (String) doc.get("stID");
            user.setStID(stID);
            String stDOB = (String) doc.get("stDOB");
            user.setStID(stDOB);
            user = new User(stName, stID, stDOB);
            list.add(user);
            System.out.println(doc);


        }
        return list;


    }


}

