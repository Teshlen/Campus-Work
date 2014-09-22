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
public class KnowledgeBase {
    public String s1 = new String();
    public String s2 = new String();
    public String s3 = new String();
    int current = 0;
    
    public String [][] base = new String[5][100];

    public KnowledgeBase() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 3; j++) {
                base[i][j] = new String();                
            }
        }
    }
    
    public void addToKnowledgeBase(String _s1,String _s2, String _s3)
    {
        s1 = _s1;
        s2 = _s2;
        s3 = _s3;
        base[current][1] = s1;
        base[current][2] = s2;
        base[current][3] = s3;
        current++;
               
    }
    
    public void displayKnowledgeBase()
    {
         for (int i = 0; i < current; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.println(base[i][j]);                
            }System.out.println("");
        }
        
    }
    /*
    @param use this function to add to the knowladge array
    */
    public void addKnowledge()
    {
        
    }
    
    
    
}
