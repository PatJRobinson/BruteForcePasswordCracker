package bruteforcepassworkcracker;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
/**
 *
 * @author p7-robinson
 */
public class Sha1Encrypter {

    private static String convertToHex(byte[] data) 
    {

        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < data.length; i++) 
        {

            int halfbyte = (data[i] >>> 4) & 0x0F;

            int two_halfs = 0;

            do {

                if ((0 <= halfbyte) && (halfbyte <= 9))

                    buf.append((char) ('0' + halfbyte));

                else

                    buf.append((char) ('a' + (halfbyte - 10)));

                halfbyte = data[i] & 0x0F;

            } while(two_halfs++ < 1);

        }

        return buf.toString();

    }

    public static String SHA1(String text)

    throws NoSuchAlgorithmException, UnsupportedEncodingException 
    {

        MessageDigest md;

        md = MessageDigest.getInstance("SHA-1");

        byte[] sha1hash = new byte[40];

        md.update(text.getBytes("iso-8859-1"), 0, text.length());

        sha1hash = md.digest();

        return convertToHex(sha1hash);

    }

    
    public static String convert_to_SHA1(String password) throws IOException 
    {

        String rawString = password;
        String result = "";
        try 
        {

            //System.out.println("SHA1 hash of string: " + Sha1Encrypter.SHA1(rawString));
            result = Sha1Encrypter.SHA1(rawString);
            
        } catch (NoSuchAlgorithmException e) 
        {

            // TODO Auto-generated catch block

            e.printStackTrace();

        } catch (UnsupportedEncodingException e) 
        {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }
        return result;
    }
}
