/*
 * Praca Inzynierska - Czesc 3 - Aplikacja Webowa do obslugi sieci Teatrow
 *
 * Klasa przechowujaca przydatne, pomocnicze metody - Utility.java
 * 
 */

package szaman.playbook.utility;

// Laczenie z reszta wlasnych klas //



// Reszta klas //

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Szaman
 */
public class Utility
{
    //Szyfrowanie hasła przy pomocy SHA-1
    public String sha1Encryptor(String password) throws NoSuchAlgorithmException 
    {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
        byte[] result = messageDigest.digest(password.getBytes());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < result.length; i++) 
        {
            stringBuilder.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }         
        return stringBuilder.toString();
    }
}
