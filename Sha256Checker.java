import java.security.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Cracker {

static Set<String> perms;
    static String crackSha256(String hash, String chars) {
     perms = new HashSet<>();
    permute(chars.toCharArray(), 0, chars.length() - 1);
    for(String s : perms){
      if(sha256(s).equals(hash)) { return s; }
    }
        return null;
    }
    
    private static void permute(char[] str, int low, int high) {
    if (low == high) {
        perms.add(new String(str, 0, str.length));
    } else {
        for (int i = low; i <= high; i++) {
            char[] x = charArrayWithSwappedChars(str, low, i);
            permute(x, low + 1, high);
        }
    }
}

private static char[] charArrayWithSwappedChars(char[] str, int a, int b) {
    char[] array = str.clone();
    char c = array[a];
    array[a] = array[b];
    array[b] = c;
    return array;
}

public static String sha256(String base) {
    try{
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(base.getBytes("UTF-8"));
        StringBuffer hexString = new StringBuffer();

        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
    } catch(Exception ex){
       throw new RuntimeException(ex);
    }
}

}
