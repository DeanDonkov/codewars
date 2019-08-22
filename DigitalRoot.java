import java.util.*;
import java.util.stream.*;

public class DRoot {
  public static int digital_root(int n) {
    int[] digits = Stream.of(Integer.toString(n).split("")).mapToInt(Integer::parseInt).toArray();
    int sum = Arrays.stream(digits).sum();
    System.out.println(sum);
    if(sum < 10){
      return sum;
    }
    while(sum > 10){
      int[] digits2 = Stream.of(Integer.toString(sum).split("")).mapToInt(Integer::parseInt).toArray();
    int sum2 = Arrays.stream(digits2).sum();
    if(sum2 < 10){
        return sum2;
      }
    }
    return 2;
  }
}
