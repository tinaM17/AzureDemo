package com.nagarro.Hibernate;

import java.util.List;

public class ProductDetails {

    public void viewTShirt(List<Shirt> list) {

        if (list.isEmpty()) {
            System.out.println("T-Shirt is Not Available.");
            return;
        }
        System.out.println("\nThis T-Shirt is Avalaible");
        System.out.println();
        System.out.println("      ID       |           NAME         |    COLOUR   |  GENDER |   SIZE   | PRICE |RATING|AVAILABILITY|");
        for (Shirt f : list) {
            System.out.print(String.format("%16s",f.getId()));
            System.out.print(String.format("%24s",f.getName()));
            System.out.print( String.format("%13s",f.getColour()));
            System.out.print(String.format("%12s",f.getGender()));
            System.out.print(String.format("%9s",f.getSize())+" ");
            System.out.print("   "+f.getPrice()+"  ");
            System.out.print(" "+f.getRating()+"   ");
            System.out.println(f.getAvailable()+" ");
        }
        System.out.println("  ");

    }

}