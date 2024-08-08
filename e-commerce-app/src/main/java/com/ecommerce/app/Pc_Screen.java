package com.ecommerce.app;
public class Pc_Screen {
    //Class representing a PC Screen and its attributes

    private double inches;

    //constructor
    public Pc_Screen(double inches) {
        this.inches = inches;
    }

    //getters and setters
    public double get_inches() {
        return this.inches;
    }

    public void set_inches(double inches) {
        this.inches = inches;
    }

    @Override
    public String toString() {
        return "PC Screen [Size: " + inches + " inches]";
    }
}