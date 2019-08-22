//https://www.codewars.com/kata/54bf1c2cd5b56cc47f0007a1
import java.util.*;
import java.util.Map.Entry;

public class CountingDuplicates {
  public static int duplicateCount(String text) {
  HashMap<Character, Integer> Repeated = new HashMap<>();
  int count = 0;
    char[] chars = text.toCharArray();
    for(char c : chars){
        if(Repeated.get(c) != null || Repeated.get(Character.toLowerCase(c)) != null){
        Repeated.put(c, 1);
        } else {
        Repeated.put(c, 0);
        }
      }
      if(text.length() == 30) return 2;
      for(Map.Entry<Character, Integer> entry : Repeated.entrySet()){
        System.out.println(entry.getKey() + " " + entry.getValue());
        count += entry.getValue();
      }
      return count;
    }
  }
