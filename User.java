package com.apporders;
import java.util.ArrayList;
import java.util.Scanner;

public class User{

    //Private access modifier
    private String name;

    //getter
    public String getName() {
        return this.name;
    }

    //setter
    public void setName(String name) {
        this.name = name;
    }
    
    public ArrayList<String> userActivity() {
        Scanner input = new Scanner(System.in);

        //ArrayList
        ArrayList<String> activity = new ArrayList<String>();
        System.out.println("Provide data");
        
        activity.add(input.next());
        input.close();
      
        return activity;
    }
}
