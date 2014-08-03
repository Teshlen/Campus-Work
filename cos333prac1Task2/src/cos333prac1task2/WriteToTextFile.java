/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cos333prac1task2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Teshlen
 */
public class WriteToTextFile {
    private final String filePath;
    FileWriter write;
    PrintWriter printLine;

    public WriteToTextFile(String path) throws IOException {
        
    filePath = path;
    write = new FileWriter(filePath, true);
    printLine = new PrintWriter(write);
    }
    
    public void writeLineToFile(String line) throws IOException
    {
        printLine.println(line);
        
        
    }
    public void closeTextFile(){
    printLine.close();}
}
