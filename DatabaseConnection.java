package com.apporders;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.bson.Document;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.types.ObjectId;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {

    private String url = "";

    String getUrl() {
        return this.url;
    }

    public MongoClient establishDbConnection() {

        Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);
        DatabaseConnection dbLink = new DatabaseConnection();

        try {
            MongoClient mongoClient = MongoClients.create(dbLink.getUrl());
            System.out.println("---------------------------------");
            System.out.println("Connected successfully to server.");
            System.out.println("---------------------------------");

            return mongoClient;

        } catch (MongoException e) {
            System.out.println("---------------------------------");
            System.err.println("An error occurred while attempting to connect: " + e);
            System.out.println("---------------------------------");
            }
        
        return null;

    }

    public void insertData(String user, Duration sessionLength, MongoClient mongoClient, ArrayList<String> activity, Object metaOne, Object metaTwo) {

        MongoDatabase database = mongoClient.getDatabase("gettingStarted");
        MongoCollection<Document> collection = database.getCollection("people");
        
        Map<String, Object> metaData = new HashMap<>();
        metaData.put("meta_one", metaOne);
        metaData.put("meta_two", metaTwo);

        System.out.println("---------------------------------");
        System.out.println("Writing data...");
        System.out.println("---------------------------------");

        collection.insertOne(new Document()
                        .append("_id", new ObjectId())
                        .append("user", user)
                        .append("session_length_in_seconds", sessionLength.toSeconds())
                        .append("meta_data", metaData)
                        .append("activity", activity)
        );

        System.out.println("---------------------------------");
        System.out.println("Data written!");
        System.out.println("---------------------------------");

        mongoClient.close();
    }
}
