/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.db4o.ObjectSet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Teshlen
 */
public class cos332 {

    public static final String NEW_LINE = "\r\n";
    public static final String br = "<br>";// new line characters.
    private ServerSocket server;
    private Socket client;
    private BufferedReader reader;
    private PrintWriter writer;
    private String name;
    private String surname;
    private String date;
    private String whom;
    private String itemSearchedFor;
    private boolean fname = false;
    private boolean fsurname = false;
    private boolean fdate = false;
    private boolean fwhom = false;
    private boolean go = false;
    private boolean options = false;
    private String name1[] = new String[10];
    private String surname1[] = new String[10];
    private String date1[] = new String[10];
    private String time1[] = new String[10];
    private String whom1[] = new String[10];
    private int inc = 0;
    private boolean start = false;
    String accountNumber;
    String password;
    Database db = new Database();
    Users x = new Users();
//private String current;

    public static void main(String[] args) {
        cos332 s = new cos332(55555);
        s.start();
    }

    public cos332(int port) {
        try {
            server = new ServerSocket(port);
        } catch (IOException ex) {
            System.out.println("Error starting server: " + ex.getMessage());
            System.exit(-1);
        }
    }

    public String displayData(String number) {

        x = db.findUserByAccountNumber(number);
        String data = "<p>";
        data += "Name : " + x.name + " " + x.surname;
        data += br;
        data += "Account Number : " + x.accountNumber;
        data += br + "</P>";

        return data;

    }
    
    public String checkAccountNumber(String accountNumber)
    {
        if(x.accountNumber.contains(accountNumber))
        {
            return " working";
        }
        else
        {
            return " not valid";
        }
    }

    public String head() {
        String ned = new String();
        ned = "";
        ned += "<head><title>TelNet Server</title>";
        ned += "<meta http-equiv=\"refresh\">";
        ned += NEW_LINE;
        ned += "<style>";
        ned += "h1 {color:purple;}";
        ned += "p {color:grey;}";
        ned += "</style>";
        ned += "</head>";
        return ned;
    }

    public String body() {
        String bod = "";

        bod += NEW_LINE;
        bod += "<body>";
        bod += "<form method=\"get\" >";
        bod += "Input:<input type=\"text\"  name=\"n\" size=\"50\">";
        bod += "<input type=\"submit\" value=\"Submit\">";
        bod += "</form>";
        bod += "</body>";

        return bod;
    }

    public String choices1() {
        String choice = "";
        choice += "<h1> Welcome to your account log in page...</h1>" ;
        choice += input("enter your name to log on", "name");
//                + "<p>" + br + "Would you like to add to the database? enter 1";
//        choice += br;
//        choice += "would you like to view items? enter 2";
//        choice += br;
//        choice += "would you like to Search for entered items? enter 3";
//        choice += br;
//        choice += "Log on? enter 4";
        choice += br + "</p>";

        return choice;
    }

    public String extract(String mes) {
        String me = "";
        int z = 0;
        start = false;
        String x = "";
        x += mes;
        String q = "";//all this does is get the inputted text
        int index1 = 0;
        int index2 = 0;
        for (int y = 0; y < 30; y++) {
            if (x.charAt(y) == '=') {
                index1 = y + 1;
            }

            if (x.charAt(y) == ' ') {
                start = true;
            }
            if (start == true && x.charAt(y) == ' ') {
                index2 = y;
            }
        }
        if (start) {
            if (z < 1) {
                q = x.substring(index1, index2);
            }
        }

        return q;
    }

    public void checkName(String mes) {
        name += extract(mes);
    }
    
    public Boolean checkCredentials(String name, String password)
    {
        Database data = new Database();
        x = data.findUserByNameAndPassword(name, password);
        System.out.println("-------------" + x.accountNumber);
        if((x.accountNumber).contains("im sorry"))
        {
            System.out.println("the user did not exist");
            return false;
        }
        else
            return true;
        
    }

    public void check(String mes) // checks the input
    {
        String current = "";
        String response = "";
        boolean found = false;
        System.out.println(mes);
        if (mes.contains("GET /?yes=yes HTTP/1.1")) {
            go = true;
        }

        if (go == true) {
            response += input("name", "name");
            go = false;
        }
        if (mes.contains("GET /?n=4")) {
            response += input("name", "name");
        }

        if (mes.contains("GET /?name=")) {
            System.out.println("sucess on name");
            name = mes;
            name = extract(name);
            System.out.println("name is: " + name);
            response += passwordInput("Password", "Password");
        }
        if (mes.contains("GET /?Password=")) {
            password = mes;
            password = extract(password);
            System.out.println("sucess on Password " + password);
            Boolean userExists = checkCredentials(name, password);
            if (userExists) {
                response += input("Please enter your account number to view history", "AccountNumber");
            } else {
                response += "<h1> I am sorry. you have entered the wrong passowrd" + br;
                response += "The program will now terminate for safety... </h1>";
            }
        }
        if (mes.contains("GET /?AccountNumber=")) {
            accountNumber = mes;
            accountNumber = extract(accountNumber);
            System.out.println("account number is:-------------- " + accountNumber);
          
            //response += input("yes if you would like to continue entering information", "yes");
            response += input("Enter 1 to go back to the log on page and log out now", "options");
            Users f = new Users();
            f = db.findUserByAccountNumber(accountNumber);
            response += "<h1> Welcome to your account " + f.name + " " + f.surname + " </h1>";
            response += br + " <h2> Your details are as follows... </h2>" ;
            //response += "<p> " + br+ "name : " + f.name + " " + f.surname+ br;
            response += "<h3>account Number : " + f.accountNumber + br;
            response += "Salary : " + f.salary + br;
            response += "Expenses : " + f.expenses + br;
            response += "</h3>";
            // response += "<p> " + br +checkAccountNumber(accountNumber) + "</p>" + br;
        }
        if (mes.contains("GET /?surname=")) {
            System.out.println("sucess on surname");
            surname = mes;
            surname = extract(surname);
            System.out.println("surname is: " + surname);
            response += input("date", "date");
        }
        if (mes.contains("GET /?date=")) {
            System.out.println("sucess on date");
            date = mes;
            date = extract(date);
            System.out.println("date is: " + date);
            response += input("whom", "whom");
        }
        if (mes.contains("GET /?whom=")) {
            System.out.println("sucess on whom");
            whom = mes;
            whom = extract(whom);
            System.out.println("whom is: " + whom);
            response += input("yes if you would like to continue entering information", "yes");
            response += input("1 in options if you would like to stop entering information and go back to the main page", "options");

            name1[inc] = name;
            surname1[inc] = surname;
            date1[inc] = date;
            whom1[inc] = whom;
            inc++;
        }
        if (mes.contains("GET /?options=")) {
            options = true;
            response += head();
            response += choices1();
            //response += body();
        }
        if (mes.contains("GET /?n=1")) {
            System.out.println("1");
            fname = true;
            response += input("name", "name");
        }
        if (mes.contains("GET /?n=2")) {
            System.out.println("2");
            response += head();
            response += "<p>";
            for (int u = 0; u < inc; u++) {
                int s = u + 1;
                response += "Entry : " + s + br;
                response += "Name : " + name1[u] + br;
                response += "Surname : " + surname1[u] + br;
                response += "Date : " + date1[u] + br;
                response += "Whom : " + whom1[u] + br + br;
            }
            response += "</p>";
            response += choices1();
            response += body();
        }
        if (mes.contains("GET /?n=3")) {
            System.out.println("3");
            response += input("any item to search for ", "search");
        }
        if (mes.contains("GET /?search=")) {
            int s = 0;
            response += head();
            response += "<p>";
            itemSearchedFor = extract(mes);
            for (int u = 0; u < inc; u++) {
                if (name1[u].equals(itemSearchedFor)) {
                    s = u;
                    found = true;
                }
                if (surname1[u].equals(itemSearchedFor)) {
                    s = u;
                    found = true;
                }
                if (date1[u].equals(itemSearchedFor)) {
                    s = u;
                    found = true;
                }
                if (whom1[u].equals(itemSearchedFor)) {
                    s = u;
                    found = true;
                }
            }
            response += br;
            if (found) {
                response += " Item found !!!" + br;
                response += "Name : " + name1[s] + br;
                response += "Surname : " + surname1[s] + br;
                response += "Date : " + date1[s] + br;
                response += "Whom : " + whom1[s] + br + br;
            } else {
                response += "Item couldnot be found.";
            }
            response += "</p>";
            response += choices1();
            //response += body();
        }
        writer.write(response);// finally wrotes response to client
    }

    public String input(String x, String n) {
        String bod = "";
        bod += "<head><title>choices</title></head>";

        bod += "<body>";
        bod += NEW_LINE + "<p>" + br + "Please enter " + x + br + "</p>";
        bod += "<form method=\"get\" >";
        bod += "Input:<input type=\"text\"  name=\"" + n + "\" size=\"50\">";
        bod += "<input type=\"submit\" value=\"Submit\">";
        bod += "</form>";
        bod += "</body>";

        return bod;
    }
    
        public String passwordInput(String x, String n) {
        String bod = "";
        bod += "<head><title>choices</title></head>";

        bod += "<body>";
        bod += NEW_LINE + "<p>" + br + "Please enter " + x + br + "</p>";
        bod += "<form method=\"get\" >";
        bod += "Input:<input type=\"password\"  name=\"" + n + "\" size=\"50\">";
        bod += "<input type=\"submit\" value=\"Submit\">";
        bod += "</form>";
        bod += "</body>";

        return bod;
    }
    
    

    public void start() {
        db.open();
        System.out.println("Server started.");
        int count = 0;
// Continuously accept new requests:
        while (true) {
            try {
                client = server.accept();
                reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                writer = new PrintWriter(client.getOutputStream());
                String message = "";
                String line = reader.readLine();

// Read all lines of message:
                while (line != null && reader.ready()) {
                    message += line + NEW_LINE;

                    line = reader.readLine();
                }
                //System.out.println(message);
                if (!message.equals("")) {
                    String response = "";

                    while (count < 2) {
                        count++;
                        response = "HTTP/1.1 200 OK" + NEW_LINE; // new line required
                        response += "Content-Type: text/html" + NEW_LINE; // new line required
                        response += NEW_LINE;// blank line required. put HTML code after blank line...    
                        response += head();
                        response += choices1();
                        //response += body();
                    }
                    writer.write(response);
                    check(message);
                }
                writer.close();
                reader.close();
                client.close();
            } catch (IOException ex) {
                System.out.println("Error accepting connection: " + ex.getMessage());
            }

        }//end while true

    }//end start()
}
