/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cos326;

import com.mongodb.*;
import com.mongodb.util.JSON;
import java.net.UnknownHostException;
import java.util.Set;

/**
 *
 * @author user
 */
public class task3 {

    private DB db;
    private MongoClient mongoClient;
    private String array;

    public void openDatabase() throws UnknownHostException {
        mongoClient = new MongoClient("localhost", 27017);
        db = mongoClient.getDB("facebookdata");

    }//close the open database file

    public void getCollections() {   //System.out.println("Collections:");
        // System.out.println(db.getCollectionNames());
        Set<String> colls = db.getCollectionNames();

        for (String s : colls) {
            System.out.println(s);
        }
    }

    public void getDocuments() {
        DBCollection collection = db.getCollection("facebook");
        DBObject myobj = collection.findOne();
        array = myobj.toString();
        System.out.println(myobj);

    }

    public void printMessages() {
        DBCollection collection = db.getCollection("facebook");
        DBObject myobj = collection.findOne();
        array = myobj.toString();
        int count = 0;

        int indexMessage1 = array.indexOf("message");
        int indexOfActions = array.indexOf("actions");
        System.out.println("message : " + array.substring(indexMessage1 + 11, indexOfActions - 3));
        count++;
        array = array.substring(indexOfActions + 5);
        indexMessage1 = array.indexOf("message");
        indexOfActions = array.indexOf("actions");
        System.out.println("message : " + array.substring(indexMessage1 + 11, indexOfActions - 3));
        count++;
        System.out.println("Number of messages: " + count);
    }

    public void addToCollection() {

        DBCollection collection = db.getCollection("facebook");
        DBObject myobj = collection.findOne();
        array = myobj.toString();
        int indexMessage1;
        int indexMessage2;
      //  for(int y = 0; y < 2; y++){
            
        indexMessage1 = array.indexOf("name");
        array = array.substring(indexMessage1);
        
        indexMessage1 = array.indexOf("name");
        indexMessage2 = array.indexOf("id");
        String name = array.substring(indexMessage1+9, indexMessage2-5);
            System.out.println(name);
        array = array.substring(indexMessage2);
        indexMessage1 = array.indexOf("id");
        indexMessage2 = array.indexOf("message");
        String id = array.substring(indexMessage1+7, indexMessage2-6);
            System.out.println(id);
        array = array.substring(indexMessage2);
        
        String message = array.substring(12,array.indexOf("actions")-5);
        System.out.println(message);
        array = array.substring(array.indexOf("actions"));
       
        db.createCollection("messages", null);
        collection = db.getCollection("messages");
        
        BasicDBObject doc = new BasicDBObject("name", name)
        .append("id", id)
        .append("message", message);
        collection.insert(doc);
        
        int s = array.indexOf("name");
       array = array.substring(s);
       array = array.substring(s);
       s = array.indexOf("name");
        array = array.substring(s);
        array = array.substring(s);
       
       //nextr one
       indexMessage1 = array.indexOf("name");
        array = array.substring(indexMessage1);
        
        indexMessage1 = array.indexOf("name");
        indexMessage2 = array.indexOf("id");
         name = array.substring(indexMessage1+9, indexMessage2-5);
            System.out.println(name);
        array = array.substring(indexMessage2);
        indexMessage1 = array.indexOf("id");
        indexMessage2 = array.indexOf("message");
        id = array.substring(indexMessage1+7, indexMessage2-6);
            System.out.println(id);
        array = array.substring(indexMessage2);
        
         message = array.substring(12,array.indexOf("actions")-5);
        System.out.println(message);
        array = array.substring(array.indexOf("actions"));
       
        db.createCollection("messages", null);
        collection = db.getCollection("messages");
        
        doc = new BasicDBObject("name", name)
        .append("id", id)
        .append("message", message);
        collection.insert(doc);
       
       
                
        
        
        
        //}//close for loop

    }
}
