package com.apporders;

import java.time.Duration;

import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.types.ObjectId;

public class db {

    private String url = "";

    String getUrl() {
        return this.url;
    }

    public static MongoCollection<Document> dbConnection() {

        db dbLink = new db();

        try (MongoClient mongoClient = MongoClients.create(dbLink.getUrl())) {

            MongoDatabase database = mongoClient.getDatabase("gettingStarted");
            //MongoCollection<Document> collection = database.getCollection("people");

            try {
                Bson command = new BsonDocument("ping", new BsonInt64(1));
                Document commandResult = database.runCommand(command);

                System.out.println("---------------------------------");
                System.out.println("Connected successfully to server.");


            } catch (MongoException e) {

                System.out.println("---------------------------------");
                System.err.println("An error occurred while attempting to run a command: " + e);
            }
        }
        return null;
    }

    public static MongoCollection<Document> dbInsert(user user, Duration sessionTime) {

        db dbLink = new db();
        
        MongoClient mongoClient = MongoClients.create(dbLink.getUrl());
        CodecRegistry pojoCodecRegistry = org.bson.codecs.configuration.CodecRegistries
                                                .fromRegistries(MongoClientSettings
                                                .getDefaultCodecRegistry(), org.bson.codecs.configuration.CodecRegistries
                                                .fromProviders(PojoCodecProvider
                                                .builder()
                                                .automatic(true)
                                                .build())); 
        MongoDatabase database = mongoClient.getDatabase("gettingStarted").withCodecRegistry(pojoCodecRegistry);;
        MongoCollection<Document> collection = database.getCollection("people");

        Document userSession = new Document("_id", new ObjectId());
        
        userSession.append("user", user).append("session time", sessionTime.toString());

        collection.insertOne(userSession);

        mongoClient.close();
        return null;
    }
}
