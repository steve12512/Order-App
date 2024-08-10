package com.ecommerce.app;
public class Workstation extends Personal_Computer {
    // Class representing a Workstation

    private String os;

    //constructor
    public Workstation(Personal_Computer personal_computer, String os) {
        super(personal_computer.get_pc_tower(), personal_computer.get_pc_screen(), personal_computer.get_hdd());
        this.os = os;
    }

    //getters and setters
    public String get_os() {
        return this.os;
    }

    public void set_os(String os) {
        this.os = os;
    }

    @Override
    public String toString() {
        return "Workstation \nPC: " + super.toString() + "\nOperating System: " + os;
    }
}