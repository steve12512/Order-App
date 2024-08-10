package com.ecommerce.app;
public class Personal_Computer extends  Product {
    //Class representing a Personal Computer

    private Pc_Tower pc_tower;
    private Pc_Screen pc_screen;
    private int hdd;

    //constructor
    public Personal_Computer(Pc_Tower pc_tower, Pc_Screen pc_screen, int hdd) {
        this.pc_tower = pc_tower;
        this.pc_screen = pc_screen;
        this.hdd = hdd;
    }

    //getters and setters
    public Pc_Tower get_pc_tower() {
        return this.pc_tower;
    }

    public Pc_Screen get_pc_screen() {
        return this.pc_screen;
    }

    public int get_hdd() {
        return this.hdd;
    }

    public void set_pc_tower(Pc_Tower pc_tower) {
        this.pc_tower = pc_tower;
    }

    public void set_pc_screen(Pc_Screen pc_screen) {
        this.pc_screen = pc_screen;
    }

    public void set_hdd(int hdd) {
        this.hdd = hdd;
    }

    @Override
    public String toString() {
        return " Pc_Tower: " + pc_tower + "\nScreen: " + pc_screen + ",\nHDD: " + hdd + " GB";
    }
}