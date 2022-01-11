/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bruteforcepassworkcracker;

//import java.util.Vector;
import java.util.*;

/**
 *
 * @author p7-robinson
 */
public class Candidate {
    
    // store position in alphabet
    private ArrayList<Integer> members;
    private int length;
    private int alphabet_length;
    
    Candidate(int alph_length)
    {
        members = new ArrayList<Integer>();
        length = 0;
        alphabet_length = alph_length;
    };
    
    public void add_member()
    {
        members.add(0);
        length++;
    }
    
    public void increment_member(int member)
    {
        if (members.get(member) >= alphabet_length)
        {
            members.set(member, 0);
            System.out.println("Warning [candidate.increment_member() : wrapping around");
        }
        else        
        {
            members.set(member, members.get(member) + 1);
        }
    }
    
    public void set_member(int member, int val)
    {
        members.set(member, val);
    }
    
    public int get_member(int member)
    {
        return members.get(member);
    }    
}
