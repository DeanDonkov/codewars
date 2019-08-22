public class Dinglemouse {
 
  public static int[] coffeeLimits(final int year, final int month, final int day) {
 
    int coffee = Integer.parseInt("cafe",16);
    int decaf = Integer.parseInt("decaf",16);
 
    String month2;
    String day2;
   
    if (month < 10){
    month2 = "0" + Integer.toString(month);
    }else{
    month2 = Integer.toString(month);
    }
    if (day < 10){
    day2 = "0" + Integer.toString(day);
    }else{
    day2 = Integer.toString(day);
    }
    String okay = Integer.toString(year) + month2 + day2;
    long helpcoff = Long.parseLong(okay);
    long helpdec = Long.parseLong(okay);
    int[] ans = {0,0};
    for (int i = 0; i < 5001; i++){
    // System.out.println(helpcoff);
   //  System.out.println(i);
    if (Long.toHexString(helpcoff).contains("dead")){
        ans[0] = i;
        break;
    }
    helpcoff += coffee;
    }
    for (int i = 0; i < 5001; i++){
   
    if (Long.toHexString(helpdec).contains("dead")){
        ans[1] = i;
        break;
       
    }
    helpdec += decaf;
    }
    return ans;
  }
 
}
