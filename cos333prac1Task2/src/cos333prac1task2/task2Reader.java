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
public class task2Reader {

    public Stack stack = new Stack();
    public Stack alternateStack = new Stack();
    public String operation1;
    public int operationCounter;
    private boolean loaded;
    String input;
    int count = 0;
    public static WriteToTextFile writeCode;

    public task2Reader() throws IOException {

        writeCode = new WriteToTextFile("code.txt");
        operation1 = "";
        loaded = false;

    }

    public void mainRun() throws FileNotFoundException, IOException {
        textReader readToFile = new textReader("input.txt");
        readToFile.openFile();
        if (count == 0) {
            input = "  ";
            count++;
        }

        input = readToFile.readToString();
        readIntoStack();

        readToFile.closeFile();
    }

    public void readIntoStack() throws IOException {
        alternateStack.push('(');
        for (int x = 0; x < input.length(); x++) {
            if (input.charAt(x) != ')') {
                stack.push(input.charAt(x));

            }
            if (input.charAt(x) == ')') {
                while ((char) stack.peek() != '(') {
                    alternateStack.push(stack.pop());
                    //alternateStack.push('@');
                }
                alternateStack.push(stack.pop());
            }
        }
        alternateStack.pop();
        System.out.println(alternateStack.toString());
        System.out.println(alternateStack.peek());

        //
        while (!alternateStack.isEmpty()) {
            if (Character.isLetter((char) alternateStack.peek()) || Character.isDigit((char) alternateStack.peek())) {
                System.out.println("LOAD " + alternateStack.peek() + ";");
                writeCode.writeLineToFile("LOAD " + alternateStack.pop() + ";");

            }
            if ((char) alternateStack.peek() == '+' || (char) alternateStack.peek() == '-' || (char) alternateStack.peek() == '*' || (char) alternateStack.peek() == '/' || (char) alternateStack.peek() == '#') {
                operation1 += alternateStack.pop();
            }
            if ((char) alternateStack.peek() == '(') {
                count++;
                alternateStack.pop();
            }
          //  System.out.println("time " + operation1);

        }
        if (count > 1) {
            for (int p = operation1.length() - 1; p > -1; p--) {
                System.out.println("EXEC " + operation1.charAt(p) + ";");
                count = 0;
                writeCode.writeLineToFile("EXEC " + operation1.charAt(p) + ";");
                //writeCode.closeTextFile();
            }
        }
//         
        writeCode.closeTextFile();
    }

}
