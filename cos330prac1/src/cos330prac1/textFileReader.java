/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cos330prac1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Teshlen
 */
public class textFileReader {
    String fileName = "temp.txt";
    String line = null;
   
    
    public textFileReader() throws FileNotFoundException {
       
    }
    
    
    public void reader() throws IOException {
         FileReader fileReader;
    BufferedReader bufferedReader;
         fileReader = new FileReader(fileName);
        bufferedReader = new BufferedReader(fileReader);
        try {
            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                
            }   } catch (IOException ex) {
            System.out.println("could not open file");
        }
        bufferedReader.close();
    }
    
    public void writer(String message) throws IOException
    {
        FileWriter fileWriter;
    BufferedWriter bufferedWriter;
    
        fileWriter = new FileWriter(fileName,true);
        bufferedWriter = new BufferedWriter(fileWriter);
        
        //bufferedWriter.write("blah");
        bufferedWriter.write( " \n"+ message);
        bufferedWriter.close();
        
    }
    
}
