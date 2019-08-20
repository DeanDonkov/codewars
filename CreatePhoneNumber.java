//https://www.codewars.com/kata/525f50e3b73515a6db000b83

import java.util.*;

public class Kata {
  public static String createPhoneNumber(int[] numbers) {
    StringBuilder sb = new StringBuilder();
    if(numbers.length == 10){
      sb.append("(");
      for(int i = 0; i <= 2; i++){
        sb.append(numbers[i]);
      }
      sb.append(") ");
      for(int i = 3; i <= 5; i++){
        sb.append(numbers[i]);
      }
      sb.append("-");
      for(int i = 6; i <= numbers.length - 1; i++){
      sb.append(numbers[i]);
      }
    }
    return sb.toString();
  }
}
