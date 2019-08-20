//https://www.codewars.com/kata/mexican-wave

import java.util.*;

public class MexicanWave {

    public static String[] wave(String str) {
          List<String> wave = new ArrayList<>();
        for(int i = 0; i < str.length(); i++){
          StringBuilder sb = new StringBuilder();
          char c = Character.toUpperCase(str.charAt(i));
          if (c == ' ') continue;
          sb.append(str);
          sb.setCharAt(i, c);
          wave.add(sb.toString());
        }
        return wave.stream().toArray(String[]::new);
    }
    
}
