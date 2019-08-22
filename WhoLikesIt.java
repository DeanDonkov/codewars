class Solution {
    public static String whoLikesIt(String... names) {
        String[] liked = names;
        if(liked.length == 1){
          return liked[0] + " likes this";
        }
        
        if(liked.length == 2){
          return liked[0] + " and " + liked[1] + " like this";
        }
        
        if(liked.length == 3){
          return liked[0] + ", " + liked[1] +  " and " + liked[2] + " like this";
        }
        
        if(liked.length > 3){
          return liked[0] + ", " + liked[1] + " and " + (liked.length - 2) + " others like this";
        }
        
        if(liked.length == 0){
          return "no one likes this";
        }
        return "";
    }
}
