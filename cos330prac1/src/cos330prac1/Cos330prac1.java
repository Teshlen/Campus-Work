/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cos330prac1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Teshlen
 */
public class Cos330prac1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
        cos330Task1 x = new cos330Task1();
        boolean exit = false;
        while(!exit){
        x.registerUser();
            System.out.println("Continue? Enter 1 to continue. Or 2 to exit");
            int t = Integer.parseInt(reader1.readLine());
            if(t == 1){exit = false;}else{exit = true;}
        
        }
      //  x.checkCredentials();
        
    }
    
}
