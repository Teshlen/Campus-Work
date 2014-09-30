/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cos326;

import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class Cos326 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException {
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        int choice;
        task3 x = new task3();
        x.openDatabase();
        System.out.println("database opened");
        while(true){
        for (int i = 1; i < 5; i++) {
             System.out.println("Enter "+i+ " for task: "+ i);
        }choice = in.nextInt();
       
        switch(choice)
        {
            case 1: x.getCollections();
                break;
            case 2: x.getDocuments();
                break;
            case 3:x.printMessages();
                break;
            case 4: x.addToCollection();
                break;
            default:
                System.out.println("wrong value entered...");
                break;
        }
            System.out.println("");
            System.out.println("");
        }//end 
        
        
        
        
    }
}
