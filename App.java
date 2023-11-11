
package com.apporders;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import com.mongodb.MongoTimeoutException;

public class App extends Entity
{

    public static void main(String[] args) {

        App entity = new App();
        Scanner scanner = new Scanner(System.in);
        Panel welcomePanel = new Panel();
        User userName = new User();

        System.out.println(welcomePanel.showMetaData());

        welcomePanel.welcomeMsg();
        userName.setName(scanner.next());

        System.out.println("user logged: " + userName.getName());

        if (userName.getName().equals("Root")) {
            rootPanel rootMsg = new rootPanel();
            rootMsg.welcomeMsg();
        }

        ArrayList<String> activity;
        activity = new ArrayList<String>();

        Instant start = Instant.now();
        System.out.println("Session has been opened");
        System.out.println("1. Abort session");
        System.out.println("2. Report performed activities");
        int userPick = scanner.nextInt(); 

        switch (userPick) {
            case 1: 
                activity = new ArrayList<>(Arrays.asList("Session aborted"));
                break;          
            case 2: 
                activity = userName.userActivity();
                break;
        }

        scanner.close();

        Instant end = Instant.now();
        Duration sessionLength = Duration.between(start, end);
        System.out.println("Session time in seconds: " + sessionLength.getSeconds());
                        
        DatabaseConnection connection = new DatabaseConnection();
                        
        try {
            connection.insertData(
                userName.getName(),
                sessionLength,
                connection.establishDbConnection(),
                activity,
                entity.getSystemEntity(),
                entity.getSystemCores()

            );
        }

        catch (MongoTimeoutException e) {
            System.err.println("Timeout error: "+ e);
        }
    }

    @Override
    public Object getSystemEntity() {
        return System.getenv().get("USERDOMAIN_ROAMINGPROFILE");
    }

    @Override
    public Object getSystemCores() {
        return System.getenv().get("NUMBER_OF_PROCESSORS");
    }

}
