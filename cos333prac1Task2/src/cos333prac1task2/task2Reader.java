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
        for (int x = 0; x < input.length(); x++) {
            stack.push(input.charAt(x));

            if ((char) stack.peek() == ')') {
                stack.pop();
                while ((char) stack.peek() != '(') {
                    // if(!stack.isEmpty())
                    alternateStack.push(stack.pop());
                }
                stack.pop();
            }
        }

        while (!alternateStack.isEmpty()) {
            if ((char) alternateStack.peek() == '+' || (char) alternateStack.peek() == '-' || (char) alternateStack.peek() == '*' || (char) alternateStack.peek() == '/' || (char) alternateStack.peek() == '#') {
                operation1 += alternateStack.pop();
                loaded = true;

            }
            if (Character.isLetter((char) alternateStack.peek()) || Character.isDigit((char) alternateStack.peek())) {
                writeCode.writeLineToFile("LOAD " + alternateStack.pop() + ";");
            }

        }
        for (int p = operation1.length() - 1; p > -1; p--) {
            writeCode.writeLineToFile("EXEC " + operation1.charAt(p) + ";");
        }
        writeCode.writeLineToFile("");
        writeCode.closeTextFile();
    }
}
