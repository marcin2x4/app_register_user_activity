
package com.apporders;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class App 
{

    public static void main( String[] args ) {

        Scanner scanner = new Scanner(System.in);
        boolean openSession = true;

        panel welcomePanel = new panel();
        welcomePanel.welcomeMsg();

        user userName = new user();
        userName.setName(scanner.next());

        System.out.println("user logged: " + userName.getName());

        if (userName.getName().equals("Root")) {
            rootPanel rootMsg = new rootPanel();
            rootMsg.welcomeMsg();
        }

        while(openSession) {
            Instant start = Instant.now();
            System.out.println("Session has been opened");
            System.out.println("1. Exit");

            int userPick = scanner.nextInt();

            switch (userPick) {
                case 1:  openSession = false;
                scanner.close();
                Instant end = Instant.now();
                Duration timeElapsed = Duration.between(start, end);
                System.out.println("Session time in seconds: " + timeElapsed.getSeconds());
                db.dbInsert(userName, timeElapsed);
                
            }
        }
    }
}
