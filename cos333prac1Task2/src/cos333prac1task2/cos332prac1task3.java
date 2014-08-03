/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cos333prac1task2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Stack;

/**
 *
 * @author Teshlen
 */
public class cos332prac1task3 {

    private String phiAlphabets;
    private String phiNumbers;
    textReader textinput;
    textReader logicInput;
    private Stack stack;
    WriteToTextFile writer1;

    public cos332prac1task3() throws FileNotFoundException, IOException {
        textinput = new textReader("phi.txt");
        textinput.openFile();
        phiAlphabets = "";
        phiNumbers = "";
        logicInput = new textReader("Code.txt");
        logicInput.openFile();
        stack = new Stack();
        writer1 = new WriteToTextFile("output.txt");
    }

    public void placeIntoArrays() throws IOException {
        String x = textinput.readToString();
        while (x != null) {
            phiAlphabets += x.charAt(0);
            phiNumbers += x.charAt(4);
            x = "";
            x = textinput.readToString();
        }
        
        String t = logicInput.readToString();
        while (t != null) {
            if (t.contains("LOAD")) {
                //stack.push(phiNumbers.charAt(index));
                //  System.out.println(t.charAt(5));
                if (Character.isLetter((char) t.charAt(5))) {
                    System.out.print(stack.toString());
                    stack.push(phiNumbers.charAt(phiAlphabets.indexOf(t.charAt(5))));
                    System.out.println(" LOAD " + t.charAt(5) + " = " + stack.peek());
                    writer1.writeLineToFile(stack.toString() + " (LOAD " + t.charAt(5) + " = " + stack.peek() + ")" );
                } else {
                    System.out.print(stack.toString());
                    stack.push(t.charAt(5));
                    System.out.println(" LOAD " + stack.peek());
                    writer1.writeLineToFile(stack.toString() + " (LOAD " + stack.peek() + ")");
                }
            }
          
            
            if (t.contains("EXEC")) {
                if(t.contains("#"))
                {
                    String c ="";c+= stack.pop();
                    int a = Integer.parseInt(c);
                    a++;
                    stack.push(a);
                }
                else{
                String c ="";c+= stack.pop();
                String v = "";v += stack.pop();
                int q = Integer.parseInt(c);
                int w = Integer.parseInt(v);
                int answer = 0;
                switch ((char) t.charAt(5)) {
                    case '+':
                        answer = q + w;
                        break;
                    case '-':
                        answer = q - w;
                        break;
                    case '/':
                        answer = q / w;
                        break;
                    case '*':
                        answer = q * w;
                        break;
                    default:
                        answer = q + w;
                }stack.push(answer);}
                
                System.out.print(stack.toString());
                System.out.println("EXEC " +t.charAt(5) );
                writer1.writeLineToFile(stack.toString() + " (EXEC " +t.charAt(5) + ")");
                
            }
            
            t = logicInput.readToString();
        }
        while(!stack.isEmpty())
            System.out.println(stack.pop());

    writer1.closeTextFile();
    }
    
}
