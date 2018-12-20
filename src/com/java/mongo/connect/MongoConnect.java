package com.java.mongo.connect;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import com.mongodb.MongoCredential;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;



import java.util.Iterator;



public class MongoConnect {
    public static void main(String[] args) {


        // Creating a Mongo client
        MongoClient mongo = new MongoClient("localhost", 27017);

        // Creating Credentials
        MongoCredential credential;
        credential = MongoCredential.createCredential("arif123", "MyData",
                "1234".toCharArray());
        System.out.println("Connected to the database successfully");

        // Accessing the database
        MongoDatabase database = mongo.getDatabase("MyData");
        System.out.println("Credentials ::" + credential);

        //Accessing the database
      //  database = mongo.getDatabase("MyData");

        //Creating a collection
        database.createCollection("Myinfo");
        System.out.println("Collection created successfully");

        // Retieving a collection
        MongoCollection<Document> collection = database.getCollection("Myinfo");
        System.out.println("Collection " + collection + " selected successfully");

        Document document = new Document("Name", "Arif")
                .append("id", 3785)
                .append("age", "50")
                .append("DOB", "01/01/1901")
                .append("url", "http://www.arif.com");


        collection.insertOne(document);
        Document document2 = new Document("Name", "Jhon")
                .append("id", 3275)
                .append("age", "30")
                .append("DOB", "01/12/1901")
                .append("url", "http://www.john.com");
        collection.insertOne(document2);

        System.out.println("Document inserted successfully");

        // Getting the iterable object
        FindIterable<Document> iterDoc = collection.find();
        int i = 1;

        // Getting the iterator
        Iterator it = iterDoc.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
            i++;}

        // Deleting the documents
            collection.deleteOne(Filters.eq("Name", "Jhon"));
            System.out.println("Document deleted successfully...");




    }
}
