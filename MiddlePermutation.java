import java.util.Arrays;

class MiddlePermutation {
    
    public static String findMidPerm(String strng) {
        
        char[] arrStr = strng.toCharArray();
        Arrays.sort(arrStr);
        StringBuilder s = new StringBuilder(new String(arrStr)).reverse();
        
        StringBuilder sb = new StringBuilder();
        sb.append(s.substring( s.length()/2, (s.length()+3)/2 ));
        sb.append(s.substring( 0,             s.length()/2    ));
        sb.append(s.substring( (s.length()+3)/2 ));
        
        return sb.toString();
    }
}
