/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cos330prac1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.db4o.*;
import com.db4o.ext.Db4oIOException;

/**
 *
 * @author Teshlen
 */
public class cos330Task1 {

    ObjectContainer db;
    //public Users[] registeredUsers;
    //private int arrayCounter;
   // public String[] level1 = new String[100];
   // public String[] level2 = new String[100];
    //public String[] level3 = new String[100];
     BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
     public Users found;
    
    public cos330Task1() {
    
    }
    public void open()
	{
            db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "users.db");
            System.out.println("Database Opened");
            
	}
    public void close()
	{
            db.close();
            System.out.println("database closed");
	}
    
    public void registerUser() throws IOException
    {
       
        System.out.println("Welcome to the user registration page...");
        System.out.println("are you already registered? 1 for yes 2 for no");
        int registered = Integer.parseInt(reader.readLine());
        if(registered == 1)
        {
            System.out.println("You may proceed...");
        }
        
        if(registered == 2){
             open();
        System.out.println("Please enter your name and surname");
        String name = reader.readLine();
        
        System.out.println("What is your access level? enter '1', '2', '3'");
        int per = Integer.parseInt(reader.readLine());
        
        System.out.println("Enter your email address...");
        String email = reader.readLine();
        
        Users newUser = new Users(per, name, email);
        db.store(newUser);
        close();
        System.out.println("Sending authentication code to " + newUser.emailAddress);
       SendEmail x = new SendEmail();
       x.sendingEmailTo("prac", newUser.authenticationCode, newUser.emailAddress);//change email address
        }
        checkCredentials(); 
    }
    
    public void checkCredentials() throws IOException // checks what level you are on
    {
        open();
        System.out.println("enter authentication code please: ");
        String usersCode = reader.readLine();
       
            ObjectSet result = db.queryByExample(new Users(0,null, null,usersCode));
            Users found = (Users) result.next();
        System.out.println("Hello " + found.name);
        System.out.println("you are on permission level: " + found.permissions);
        close();
        if(found.permissions == 1)
        {
            System.out.println("I am sorry, you are at too low a permission (permission level: " + found.permissions + ") to access the asset... :( ");
        }
        if(found.permissions == 2)
        {
            System.out.println("(permission level: " + found.permissions + ") you can read the file...");
            System.out.println("Would you like to read the file " + found.name + "? yes/no");
            String input = reader.readLine();
            if(input.equals("yes"))
            {
                textFileReader x = new textFileReader();
                System.out.println("here are the names in the text file...");
                x.reader();
            }
        }
        if(found.permissions == 3)
        {
            textFileReader x = new textFileReader();
            System.out.println("(permission level: " + found.permissions + ") you can read + write to the file...");
            System.out.println("Would you like to read or write to the file " + found.name + "?");
            System.out.println("enter 1 for read or 2 for write");
            int input = Integer.parseInt(reader.readLine());
            while(input == 1 || input == 2){
                
            if(input == 1)
            {
                x.reader();
            }
            if(input == 2)
            {
                System.out.println("Enter your name to add it to the protected text file...");
                String name = reader.readLine();
                x.writer(name);
                x.reader();
            }
            System.out.println("enter 1 for read or 2 for write 3 for exit");
            input = Integer.parseInt(reader.readLine());
            }
        
        }
    }
    
    
    
    
    
}
