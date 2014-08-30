/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class Users {
    
    public String name;
    public String surname;
    public String accountNumber;
    public String password;
    //billing info
    public String salary;
    public String expenses;
   

    public Users(String name, String surname, String accountNumber, String salary, String expenses, String pass) {
        this.name = name;
        this.surname = surname;
        this.accountNumber = accountNumber;
        this.salary = salary;
        this.expenses = expenses;
        this.password = pass;
    }



    public Users() {
        this.name = null;
        this.surname = null;
        this.accountNumber = null;
        this.salary = null;
        this.expenses = null;
        this.password = null;
        
    }
    
    
    
}
