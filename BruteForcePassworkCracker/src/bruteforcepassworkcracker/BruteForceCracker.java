/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bruteforcepassworkcracker;

import java.util.*;

/**
 *
 * @author p7-robinson
 */
public class BruteForceCracker {
    
    private ArrayList<char[]> alphabets;
    private int num_alphabets;
    private int LENGTH_MAX;
    private int alphabet_selection;
    
    
    BruteForceCracker()
    {
        alphabets = new ArrayList<char[]>();
        num_alphabets = 0;        
    }

    BruteForceCracker(int length)
    {
        alphabets = new ArrayList<char[]>();
        LENGTH_MAX = length;
        num_alphabets = 0;
    }
    
        /* Helpers ----------------------------------------
    ------------------------------------------------------*/
    
    
    // replace one character in a string
    private String replace_char(String s, int index, char c)
    {
        String result = null;
        if (index >= s.length())
        {
            System.out.println("Error [replace char] : index is greater than string length");
        }
        else
        {
            result = s.substring(0, index) + c + s.substring(index + 1);

            // Print the modified string
            System.out.println("Modified String = " + s);
        }
        
        return result;
    }
    
    public void add_alphabet(char[] alphabet)
    {
        alphabets.add(alphabet);
        num_alphabets++;
    }
    
    public int get_num_alphabets()
    {
        return num_alphabets;
    }
    
    public int get_max_length()
    {
        return LENGTH_MAX;
    }
    
    // for every possible combination of password characters, hash that combination and compare with entered hash
    public String crack_password(String hash)
    {
        
        // String to hold our password once found
        String found = null;
        
        //1. construct our initial candidate solution
        
        String candidate = "";
        
        // 2. for each length of password 
        for (int i = 0; i < LENGTH_MAX; i++)
        {
            candidate = "";
            for (int x = 0; x < i; x++)
            {
                candidate += alphabets.get(alphabet_selection)[0];
            }
            
            // 3. for position = 0, try every possible character from alphabet
            for (int x = 0; x < alphabets.get(alphabet_selection).length; x++)
            {
                candidate = replace_char(candidate, 0, alphabets.get(alphabet_selection)[x]);
                try
                {
                    String hashed_candidate = Sha1Encrypter.convert_to_SHA1(candidate);
                    if (hashed_candidate.equals(hash))
                    {
                        found = hashed_candidate;
                        return found; // Make sure breaks out of all loops ----------------------------------------------------------<<<<<<<<<<<<<<<
                    }
                }
                catch (Exception e)
                {
                    System.out.println("Error [crack password] : Something went wrong encrypting the candidate");
                }
            }
            
            // increment character , go to step 1
            // else if next highest position exists go to step 2
            // else finish 

        
        }
        
        
        
                 
        

        
        return "";
    } 

}

