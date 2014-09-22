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
public class Rules {
    public int ruleNumber;

    public Rules() {
    }
    
    public void rule1(States state , int n)
    {
        state.currentStateValue = n;
    }  
    
    public States rule2(StateArray sarray , int X)
    {
        return sarray.states[X];
    }
    
    public void rule3()
    {
        int n0, n1,n;
        
        
    }
    
    
}
