import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
* Displays a simple test page on the browser. Prints the request message from
* the client on the console.
*/
public class TestServer {
public static final String NEW_LINE = "\r\n";
public static final String br = "<br>";// new line characters.

private ServerSocket server;
private Socket client;
private BufferedReader reader;
private PrintWriter writer;
private Date date = new Date();
private String current = "";
private TimeZone sa = TimeZone.getTimeZone("Africa/Windhoek");
private TimeZone usa = TimeZone.getTimeZone("America/Godthab");
private TimeZone to = TimeZone.getTimeZone("Asia/Tokyo");
private TimeZone au = TimeZone.getTimeZone("Australia/Perth");
private TimeZone lon = TimeZone.getTimeZone("Europe/London");


 public static void main(String[] args)
{
TestServer s = new TestServer (55555);
s.start();
}


public TestServer(int port) {
try {
server = new ServerSocket(port);
}
catch (IOException ex) {
System.out.println("Error starting server: " + ex.getMessage());
System.exit(-1);
}
}

public String head()
{
    String ned = new String();
    ned = "";
    ned += "<head><title>southafrica</title>";
      ned += "<meta http-equiv=\"refresh\" content=\"1\">";
      ned += NEW_LINE;
      ned += "<style>";
      ned += "h1 {color:purple;}";
      ned += "p {color:grey;}";
      //ned += "backgroud {color : grey}";
      ned += "</style>";
      ned += "</head>";
      return ned;
}

public void check(String mes) // checks the input
{
    Calendar calender = Calendar.getInstance();
    Calendar saCalender = Calendar.getInstance();
    String response = "";
    
     if((current == "new" ) && mes.contains("GET / HTTP/1.1"))
     {
         response += "<head><title>main</title>";
   // response += "<meta http-equiv=\"refresh\" content=\"1\">";
    response += "</head>";
    response += "<body><h1>Welcome to the time! </h1>";
    response += "<h2>";
    response += "Click on the country that you would like to see the time of: ";
//    response += date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
    response += "</h2>";
    response += "<a href=SouthAfrica>South Africa</a>" + NEW_LINE;
    response += "<a href=London>London</a>" + NEW_LINE;
    response += "<a href=Tokyo>Tokyo</a>" + NEW_LINE;
    response += "<a href=USA>USA</a>" + NEW_LINE;
    response += "<a href=Austrailia>Austrailia</a>" + NEW_LINE;
    response += "</body>";
     }
    
    
    if(mes.contains("GET /SouthAfrica HTTP/1.1") || ((current == "SouthAfrica" ) && mes.contains("GET / HTTP/1.1")))
    {
        System.out.println("success on southafrica"); current = "SouthAfrica";
        
//        response += "<head><title>southafrica</title>";
//        response += "<meta http-equiv=\"refresh\" content=\"1\">";
//        response += "</head>";
        response += head();
        response += "<body><h1>The time in South Africa is:</h1>";
        response += "<h2>";
        //response += "The time in SOUTH AFRICA is: ";
        //date = new Date();
        calender.setTimeZone(sa); 
        response += calender.getTimeZone().getID() +":" +  calender.get(Calendar.HOUR_OF_DAY) + ":" + calender.get(Calendar.MINUTE) + ":" + calender.get(Calendar.SECOND);// + ":" + calender.get(Calendar.MILLISECOND);
        response += "  Date: " + calender.get(Calendar.DATE) + " / " + calender.get(Calendar.MONTH) + " / " + calender.get(Calendar.YEAR) ;
        //calender.setTimeZone(369);
       // response += date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
        //response += calender.getTime();
        response += "</h2>";
        response += "<a href=SouthAfrica>South Africa</a>" + NEW_LINE;
        
        System.out.println(date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds());
        
        response += "<a href=London>London</a>" + NEW_LINE;
        response += "<a href=Tokyo>Tokyo</a>" + NEW_LINE;
        response += "<a href=USA>USA</a>" + NEW_LINE;
        response += "<a href=Austrailia>Austrailia</a>" + NEW_LINE;
        response += "</body>";         
    }
    else System.out.println("failure on sa");
    
    if(mes.contains("GET /London HTTP/1.1") || ((current == "London" ) && mes.contains("GET / HTTP/1.1")))
    {
        System.out.println("success on London"); current = "London";
        
//        response += "<head><title>London</title>";
//        response += "<meta http-equiv=\"refresh\" content=\"1\">";
//        response += "</head>";
        response += head();
        response += "<body><h1>The time in London is: </h1>";
        response += "<h2>";date = new Date();
        //response += calender.getTime();
        calender.setTimeZone(lon); 
        response += calender.getTimeZone().getID() +":" +  calender.get(Calendar.HOUR_OF_DAY) + ":" + calender.get(Calendar.MINUTE) + ":" + calender.get(Calendar.SECOND);// + ":" + calender.get(Calendar.MILLISECOND);
        response += "  Date: " + calender.get(Calendar.DATE) + " / " + calender.get(Calendar.MONTH) + " / " + calender.get(Calendar.YEAR) ;
       // response += "The time in London is: ";
       // response += date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
        
        response += "<p>";
        response += br;
        response += br;
        response += "South Africa's time is:";
        response += br;
        response += calender.getTime();
        response += "</p>";
        
        
        response += "</h2>";
        response += "<a href=SouthAfrica>South Africa</a>" + NEW_LINE;
        response += "<a href=London>London</a>" + NEW_LINE;
        response += "<a href=Tokyo>Tokyo</a>" + NEW_LINE;
        response += "<a href=USA>USA</a>" + NEW_LINE;
        response += "<a href=Austrailia>Austrailia</a>" + NEW_LINE;
        response += "</body>";         
    }
    else System.out.println("failure on sa");
    
    if(mes.contains("GET /Tokyo HTTP/1.1") || ((current == "Tokyo" ) && mes.contains("GET / HTTP/1.1")))
    {
        System.out.println("success on Tokyo"); current = "Tokyo";
        
//        response += "<head><title>Tokyo</title>";
//        response += "<meta http-equiv=\"refresh\" content=\"1\">";
//        response += "</head>";
        response += head();
        response += "<body><h1>The time in Tokyo is: </h1>";
        response += "<h2>";date = new Date();//response += calender.getTime();
        calender.setTimeZone(to); 
        response += calender.getTimeZone().getID() +":" +  calender.get(Calendar.HOUR_OF_DAY) + ":" + calender.get(Calendar.MINUTE) + ":" + calender.get(Calendar.SECOND);// + ":" + calender.get(Calendar.MILLISECOND);
        response += "  Date: " + calender.get(Calendar.DATE) + " / " + calender.get(Calendar.MONTH) + " / " + calender.get(Calendar.YEAR) ;
       // response += "The time in Tokyo is: ";
        //response += date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
        response += "<p>";
        response += br;
        response += br;
        response += "South Africa's time is:";
        response += br;
        response += calender.getTime();
        response += "</p>";
        
        response += "</h2>";
        response += "<a href=SouthAfrica>South Africa</a>" + NEW_LINE;
        response += "<a href=London>London</a>" + NEW_LINE;
        response += "<a href=Tokyo>Tokyo</a>" + NEW_LINE;
        response += "<a href=USA>USA</a>" + NEW_LINE;
        response += "<a href=Austrailia>Austrailia</a>" + NEW_LINE;
        response += "</body>";         
    }
    else System.out.println("failure on sa");
    
    if(mes.contains("GET /USA HTTP/1.1") || ((current == "USA" ) && mes.contains("GET / HTTP/1.1"))  )
    {
        System.out.println("success on USA");  current = "USA";
        
//        response += "<head><title>USA</title>";
//        response += "<meta http-equiv=\"refresh\" content=\"1\">";
//        response += "</head>";
        response += head();
        response += "<body><h1>The time in USA is: </h1>";
        response += "<h2>";date = new Date();//response += calender.getTime();
        calender.setTimeZone(usa); 
        response += calender.getTimeZone().getID() +":" +  calender.get(Calendar.HOUR_OF_DAY) + ":" + calender.get(Calendar.MINUTE) + ":" + calender.get(Calendar.SECOND);// + ":" + calender.get(Calendar.MILLISECOND);
        response += "  Date: " + calender.get(Calendar.DATE) + " / " + calender.get(Calendar.MONTH) + " / " + calender.get(Calendar.YEAR) ;
        //response += "The time in USA is: ";
        //response += date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
        response += "<p>";
        response += br;
        response += br;
        response += "South Africa's time is:";
        response += br;
        response += calender.getTime();
        response += "</p>";
        
        response += "</h2>";
        response += "<a href=SouthAfrica>South Africa</a>" + NEW_LINE;
        response += "<a href=London>London</a>" + NEW_LINE;
        response += "<a href=Tokyo>Tokyo</a>" + NEW_LINE;
        response += "<a href=USA>USA</a>" + NEW_LINE;
        response += "<a href=Austrailia>Austrailia</a>" + NEW_LINE;
        response += "</body>";         
    }
    else System.out.println("failure on sa");
    
    if(mes.contains("GET /Austrailia HTTP/1.1") || ((current == "Austrailia" ) && mes.contains("GET / HTTP/1.1")))
    {
        System.out.println("success on Austrailia"); current = "Austrailia";
        
//        response += "<head><title>Austrailia</title>";
//        response += "<meta http-equiv=\"refresh\" content=\"1\">";
//        response += "</head>";
        response += head();
        response += "<body><h1>The time in Austrailia is: </h1>";
        response += "<h2>";date = new Date();
        //Calendar cal = new GregorianCalendar(au);cal.set
        
       // response += cal.getTime();
        //calender.setTimeZone(TimeZone.getTimeZone("Australia/Perth"));
        calender.setTimeZone(au); 
        response += calender.getTimeZone().getID() +":" +  calender.get(Calendar.HOUR_OF_DAY) + ":" + calender.get(Calendar.MINUTE) + ":" + calender.get(Calendar.SECOND);// + ":" + calender.get(Calendar.MILLISECOND);
        response += "  Date: " + calender.get(Calendar.DATE) + " / " + calender.get(Calendar.MONTH) + " / " + calender.get(Calendar.YEAR) ;
        //response += "The time in Austrailia is: ";
        //response += date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
        response += "<p>";
        response += br;
        response += br;
        response += "South Africa's time is:";
        response += br;
        response += calender.getTime();
        response += "</p>";
        
        response += "</h2>";
        response += "<a href=SouthAfrica>South Africa</a>" + NEW_LINE;
        response += "<a href=London>London</a>" + NEW_LINE;
        response += "<a href=Tokyo>Tokyo</a>" + NEW_LINE;
        response += "<a href=USA>USA</a>" + NEW_LINE;
        response += "<a href=Austrailia>Austrailia</a>" + NEW_LINE;
        response += "</body>";         
    }
    else System.out.println("failure on sa");
    
   
    
    writer.write(response);// finally wrotes response to client
}




public void start() {
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

// For some reason, the browser sends an empty message sometimes
// so ignore when this happens:
if (!message.equals("")) 
{
   
// Assemble the response message:
//System.out.println("Client sent message:" + NEW_LINE + message);

String response = "";
    while(count < 1)
    {count ++;
    
    //current = "new";
    response = "HTTP/1.1 200 OK" + NEW_LINE; // new line required
    response += "Content-Type: text/html" + NEW_LINE; // new line required
    response += NEW_LINE;// blank line required. put HTML code after blank line...
    
   // response += "<meta http-equiv=\"refresh\" content=\"1\">";
    response += "<head><title>main</title>";
    //response += "<meta http-equiv=\"refresh\" content=\"1\">";
    response += "</head>";
    response += "<body><h1>Welcome to the time! </h1>";
    response += "<h2>";
    response += "Click on the country that you would like to see the time of: ";
//    response += date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
    response += "</h2>";
    response += "<a href=SouthAfrica>South Africa</a>" + NEW_LINE;
    response += "<a href=London>London</a>" + NEW_LINE;
    response += "<a href=Tokyo>Tokyo</a>" + NEW_LINE;
    response += "<a href=USA>USA</a>" + NEW_LINE;
    response += "<a href=Austrailia>Austrailia</a>" + NEW_LINE;
    response += "</body>";
    }
 //check(message);
    
    writer.write(response); // send response to client.
     check(message);current = "new";
}
else
System.out.println("Client sent an empty request.");

// Close resources:
writer.close();
reader.close();
client.close();
}
catch (IOException ex) {
System.out.println("Error accepting connection: " + ex.getMessage());
}
}
}
}

