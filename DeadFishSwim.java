import java.util.*;
public class DeadFish {
    public static int[] parse(String data) {
    char[] arr = data.toCharArray();
    ArrayList<Integer> values = new ArrayList<>();
    int value = 0;
      for(char c : arr){
        switch(c){
        case 'i':
          value++;
          continue;
        case 'd':
          value--;
          continue;
        case 's':
          value = value * value;
          continue;
        case 'o':
          values.add(value);
          continue;
        }
      }
      int[] ret = new int[values.size()];
      for(int i = 0; i < values.size(); i++){
        ret[i] = values.get(i);
      }
      return ret;
    }
}
