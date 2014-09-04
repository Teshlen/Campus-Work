
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Teshlen
 */
public class Cos330Prac2 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
        
        cos332 s = new cos332(55555);
        Database x = new Database();
        int choice = 0;
        System.out.println("would you like to add users to the database? enter 1 for yes or 2 for no");
        choice = Integer.parseInt(reader1.readLine());
        while (choice == 1)
        {
            x.addPeople();
            System.out.println("would you like to add users to the database? enter 1 for yes or 2 for no");
            choice = Integer.parseInt(reader1.readLine());
        }
               
        s.start();
    }
    
}
