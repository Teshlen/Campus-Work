/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cos330prac1;

import java.util.Random;

/**
 *
 * @author Teshlen
 */
public final class Users {
    public int permissions;
    public String name;
    public String emailAddress;
    public String authenticationCode;

    public Users(int permissions, String name, String emailAddress) {
        this.permissions = permissions;
        this.name = name;
        this.emailAddress = emailAddress;
        this.authenticationCode = "cos330" + generateCode();
    }

    public Users(int permissions, String name, String emailAddress, String authenticationCode) {
        this.permissions = permissions;
        this.name = name;
        this.emailAddress = emailAddress;
        this.authenticationCode = authenticationCode;
    }
    
    
    public int generateCode()
    {
        Random randomGenerator = new Random();
        int code = randomGenerator.nextInt(100000000);
        return code;
    }
    
    
    
    
}
