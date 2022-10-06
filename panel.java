package com.apporders;
import java.util.ArrayList;
import java.util.Scanner;

public class panel {
    
    //Proteted access modifier
    protected void welcomeMsg() {
        System.out.println("Please log in");
    }

    public ArrayList<String> userActivity() {
        Scanner input = new Scanner(System.in);

        //ArrayList
        ArrayList<String> activity = new ArrayList<String>();
        System.out.println("what activity are you doin?");
        activity.add(input.next());

        input.close();
        return activity;
    }

}

//Inheritance
class rootPanel extends panel {
    
    //polymorphism by overriding
    @Override
    protected void welcomeMsg() {
        System.out.println("Root logged in, please bare in mind that you are ROOT!");
    }
 }
