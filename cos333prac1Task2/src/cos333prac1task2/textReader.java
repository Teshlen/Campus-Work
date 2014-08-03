/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cos333prac1task2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Teshlen
 */
public class textReader {
    private String filePath;
    FileInputStream fis;
    BufferedReader lineReader;
    String line;

    public textReader(String path) {
        filePath = path;
    }
    public void openFile() throws FileNotFoundException
    {
        try{
       fis = new FileInputStream(filePath);
       lineReader = new BufferedReader(new InputStreamReader(fis));
        }
        catch (FileNotFoundException e)
        {
            System.err.println("file not found, please try again.......");
        }
    }
    public String readToString() throws IOException
    {
        line = lineReader.readLine();       
      //  System.out.println(line);
        return line;
           
        
    }
    
    public void closeFile() throws IOException
    {
        lineReader.close();
        fis.close();
    }
    
}
