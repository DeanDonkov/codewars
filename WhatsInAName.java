import java.util.stream.Collectors;
import java.util.stream.Stream;
public class Kata{
    public static boolean nameInStr(String str, String name){
          return str.matches(Stream.of(name.split("")).map(i->"("+i+")").collect(Collectors.joining(".*", ".*", ".*")));
      }
}
