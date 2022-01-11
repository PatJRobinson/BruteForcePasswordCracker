/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bruteforcepassworkcracker;

import java.util.*;
import java.lang.Math;

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
    
    public void set_length(int length)
    {
        LENGTH_MAX = length;
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
    
    public void set_alphabet(int selection)
    {
        alphabet_selection = selection;
    }
    
    public int get_alphabet_selection()
    {
        return alphabet_selection;
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
        

        char[] current_alphabet = alphabets.get(alphabet_selection);
        
        // 2. for each length of password 
        for (int i = 1; i <= LENGTH_MAX; i++)
        {
            
            // construct our candidate, with length set from loop
            Candidate candidate = new Candidate(current_alphabet.length);
            String s = "";
            for (int x = 0; x < i; x++)
            {
                candidate.add_member();
            }
            
            
            for (int y = 0; y < Math.pow(current_alphabet.length, i - 1); y++)
            {
                
                // 3. for position = 0, try every possible character from alphabet
                for (int x = 0; x <current_alphabet.length; x++)
                {
                    s = replace_char(s, 0, current_alphabet[x]);
                    try
                    {
                        String hashed_candidate = Sha1Encrypter.convert_to_SHA1(s);
                        if (hashed_candidate.equals(hash))
                        {
                            found = hashed_candidate;
                            return found; // Make sure breaks out of all loops ----------------------------------------------------------<<<<<<<<<<<<<<<
                        }
                    }
                    catch (Exception e)
                    {
                        System.out.println("Error [crack password] : Something went wrong encrypting the candidate");
                        return null;
                    }
                }
                int pos = 1;
                while (pos < i)
                {
                    // increment character , go to step 1
                    if (candidate.get_member(pos) == current_alphabet[current_alphabet.length - 1])
                    {
                        candidate.set_member(pos, 0);
                        s = replace_char(s, pos, current_alphabet[0]);
                        pos++;
                    }
                    else
                    {
                        candidate.increment_member(pos);
                        s = replace_char(s, pos, current_alphabet[candidate.get_member(pos)]);
                        pos = i;
                    }
                }
            }                           
        }
        return null;
    } 

}

