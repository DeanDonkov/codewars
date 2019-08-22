import java.util.ArrayList;
import java.util.*;

public class FindOutlier{
  static int find(int[] integers){
  List<Integer> oddNums = new ArrayList<>();
  List<Integer> evenNums = new ArrayList<>();
    for(int i : integers) {
      if(i % 2 == 0) {
        evenNums.add(i);
      } else {
        oddNums.add(i);
      }
  }
  if(evenNums.size() > oddNums.size()){
    for(int i : oddNums){
        return i;
    }
  } else {
    for(int i : evenNums){
      return i;
    }
  }
    return 0;
  }

}
