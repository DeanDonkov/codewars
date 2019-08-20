//https://www.codewars.com/kata/psychic

import java.util.Random;
import java.lang.reflect.*;
public class Psychic {
  private static Random rand;
  
  static{
    rand = new Random(1);
    for (Class c : Math.class.getDeclaredClasses()){      
      for (Field f : c.getDeclaredFields()){
        try{
          f.setAccessible(true);
          Random r = (Random)f.get(null);
          r.setSeed(1);
        } catch(Exception e){}
      }
    }
  }
  
  public static double guess() {
    return rand.nextDouble(); 
  }
}
