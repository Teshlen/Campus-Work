
import com.db4o.*;
import com.db4o.ObjectContainer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author user
 */
public class Database {

    public ObjectContainer db;
    public BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void open() {
        db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "users.db");
        System.out.println("Database Opened");

    }

    public void close() {
        db.close();
        System.out.println("database closed");
    }

    public Users findUserByAccountNumber(String number) {

        ObjectSet result = db.queryByExample(new Users(null, null, number, null, null, null));
        if (result.size() == 0) {
            return new Users("im sorry", " it does not work", null, null, null, null);
        } else {
            Users found = (Users) result.next();
            return found;
        }
    }

    public Users findUserByNameAndPassword(String name, String password) {

        ObjectSet result = db.queryByExample(new Users(name, null, null, null, null, password));
        Users found = (Users) result.next();
        if (result.size() == 0) {
            System.out.println("i get here1");
            Users y = new Users("im sorry", " it does not work", null, null, null, null);
            return y;
        } else {

            System.out.println("i get here 2");
            return found;
        }
    }

    public static void ListResult(ObjectSet result) {
        System.out.println(result.size());
        while (result.hasNext()) {
            System.out.println(((Users) result.next()).name);
        }
    }

    public  void addPeople() throws IOException {
        open();
        Users x = new Users();
        System.out.println("enter name: ");
        x.name = reader.readLine();
        System.out.println("enter surname: ");
        x.surname = reader.readLine();
        System.out.println("enter account number: ");
        x.accountNumber = reader.readLine();
        System.out.println("enter password: ");
        x.password = reader.readLine();
        System.out.println("enter email address: ");
        x.emailAddress = reader.readLine();
        System.out.println("pass: " + x.password);
        System.out.println("enter salary: ");
        x.salary = reader.readLine();
        System.out.println("enter expenses : ");
        x.expenses = reader.readLine();
        db.store(x);
        db.close();

    }

}
