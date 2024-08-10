package com.ecommerce.app;
public class Pc_Tower extends  Product{
    //Class representing a PC Tower and its attributes

    private double memory_size;
    private double cpu_freq;

    //constructor
    public Pc_Tower(double memory_size, double cpu_freq) {
        this.memory_size = memory_size;
        this.cpu_freq = cpu_freq;
    }

    //getters and setters
    public double get_memory_size() {
        return this.memory_size;
    }

    public double get_cpu_freq() {
        return this.cpu_freq;
    }

    
    public void set_memory_size(double memory_size) {
        this.memory_size = memory_size;
    }

    public void set_cpu_freq(double cpu_freq) {
        this.cpu_freq = cpu_freq;
    }

    @Override
    public String toString() {
        return "Memory Size: " + memory_size + " GB, CPU Frequency: " + cpu_freq + " GHz";
    }
}