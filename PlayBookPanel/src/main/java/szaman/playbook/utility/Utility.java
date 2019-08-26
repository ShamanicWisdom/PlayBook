/*
 * Praca Inzynierska - Czesc 2 - Panel Administracyjny do obslugi sieci Teatrow
 *
 * Klasa pomocnicza przechowujaca zbior uzytecznych metod dla aplikacji - Utility.java.
 * 
 */

package szaman.playbook.utility;

// Laczenie z reszta wlasnych klas //

// Reszta klas //

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 *
 * @author Szaman
 *
 */

public class Utility 
{
    //Wersja aplikacji.
    String applicationVersion = "1.0.0";
    
    
    /********************UTILITY***************************/
    
    //Pull samego numeru wersji.
    public String pullVersion()
    {
        String version = "Wersja " + applicationVersion;
        return version;
    }
    
    //Pull numeru wersji i daty.
    public String pullVersionWithDate()
    {
        File file = new File("src/main/java/szaman/playbook/utility/Utility.java");
        //Odczyt daty ostatniej modyfikacji Utility.java
        Date date = new Date(file.lastModified());
        //formatowanie danych z daty.
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd, HH:mm");
        String version = pullVersion() + ", " + dateFormat.format(date);
        return version;
    }
    
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
