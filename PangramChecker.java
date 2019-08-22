
public class PangramChecker {

  public boolean check(String sentence){
    HashSet<Character> chars = new HashSet<>();
    String sent2 = sentence.replaceAll("[^a-zA-Z]", "");
    System.out.println(sent2);
    System.out.println("-------------------");
    for(int i = 0; i < sent2.length(); i++){
      
      chars.add(sent2.charAt(i));
    }
    chars.forEach(System.out::print);
    if(chars.size() == 27) return true;
    if(chars.size() == 26) return true;
    return false;
  }
}
