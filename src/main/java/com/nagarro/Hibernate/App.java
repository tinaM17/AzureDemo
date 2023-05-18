package com.nagarro.Hibernate;

import java.util.Scanner;


public class App {

    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        String color,size,gender;
        int output;
        char ch='Y';
        ProductView find=new ProductView();
        find.start();

        while(Character.toUpperCase(ch)=='Y')
        {

            System.out.print("Choose The Colour Of The T-Shirt : ");
            color = sc.nextLine();
            System.out.print("Choose The Size Of T-Shirt:(S/M/L/XL):");
            size = sc.nextLine();
            System.out.print("Enter Gender:(M (Male) / F (Female) / U(Other):");
            gender = sc.nextLine();
            System.out.print("Select Output Preference Do You Want: " +
                    "\nPress 1 For Sort by Price " +
                    "\nPress 2 For Sort by Rating " +
                    "\nPress 3 For Sort by Both " +
                    "\nSelect Your Choice Between 1 to 3: ");
            output = sc.nextInt();


            find.readFile(color, size, gender, output);
          

            System.out.println("Do You Want To Search Again? Y/N:-");
            ch=sc.next().charAt(0);
            sc.nextLine();

        }
        sc.close();
        find.stopThread();

    }

}
