package com.ecommerce.app;

public abstract  class Product {
    //all classes are children of this class. the reason it was created was to be able to pass object of all the other classes to the same method without having to write that method with a different signature.

    @Override
    public String toString(){
        return null;
    }
}
