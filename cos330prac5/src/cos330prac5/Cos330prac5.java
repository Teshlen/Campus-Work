/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cos330prac5;

/**
 *
 * @author Teshlen
 */
public class Cos330prac5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String message = "This is a test message to encrypt and decrypt";
        String passPhrase = "Ethics";
        
        System.out.println("Message to encrypt: ");
        System.out.println(message);
        System.out.println("");
        System.out.println("Pass Phrase:");
        System.out.println(passPhrase);
        System.out.println("");
        System.out.println("Encoded:");
        
        message = message.replaceAll("\\s+","");
        message = message.toLowerCase();        
        passPhrase = passPhrase.toLowerCase();        
        cypher x = new cypher(message, passPhrase);
        x.createTableau();
        
        String encodedMessage = x.encode();
        
        
        System.out.println("");
        System.out.println("Message to decrypt:");
        System.out.println(encodedMessage);
        
        System.out.println("");
        System.out.println("Decoded Message:");
        
        //to test encoded message:
        //edit this next line:
        //encodedMessage = ;
        String decryptedMessage = x.decrypt(encodedMessage);
        
        System.out.println(decryptedMessage);
        
//        if(encodedMessage.matches("SGFXSOCIYXEDQZTTYPSNYLWWEOGTHURSACCHS"))
//        {
//            System.out.println("it works :)!!!!!!!");
//        }
        System.out.println("");
        System.out.println("Here is the generated Vigenere table:");
        x.printTable();
    }
    
}
