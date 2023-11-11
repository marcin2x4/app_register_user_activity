package com.apporders;

public class Panel implements Imeta{
    
    //Proteted access modifier
    protected void welcomeMsg() {
        System.out.println("Please log in");
    }
}

//Inheritance
class rootPanel extends Panel{
    
    //polymorphism by overriding
    @Override
    protected void welcomeMsg() {
        System.out.println("Root logged in, please bare in mind that you are ROOT!");
    }
 }
