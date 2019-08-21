// kata: https://www.codewars.com/kata/583203e6eb35d7980400002a [6kyu]

import java.util.*;

public class SmileFaces {
  
  public static int countSmileys(List<String> arr) {
     int smiles = 0;
     for(String s : arr){
       
       if(s.charAt(0) == ':' || s.charAt(0) == ';'){
         if(s.length() == 2){
           if(s.charAt(1) == 'D' || s.charAt(1) == ')'){
             smiles++;
           }
         } else if(s.length() == 3){
           if(s.charAt(1) == '-' || s.charAt(1) == '~') {
             if(s.charAt(2) == 'D' || s.charAt(2) == ')') {
               smiles++;
             }
           }
         }
           
         }
  }
  return smiles;
 }
}
