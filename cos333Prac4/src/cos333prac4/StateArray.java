/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cos333prac4;

/**
 *
 * @author Teshlen
 */
public class StateArray {
    
    public States [] states;

    public StateArray() {
        states = new States[5];
        for(int x = 0; x < 5; x++)
        {
            states[x] = new States();
        }
    }
    
    
    
}
