package com.ecommerce.app;



public class App {
//main class containing the main method. it just creates a gui object  and calls the method to create the first window (from which all else will follow)
    public static void main( String[] args )
    {
        EShopGUI gui = new EShopGUI();
        gui.createAndShowGUI();
    }
}
