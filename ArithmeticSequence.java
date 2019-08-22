//https://www.codewars.com/kata/540f8a19a7d43d24ac001018/

public class Sequence {
  public static int nthterm(int first, int n, int c) {
    // Using math properties for arithmetical sequences: f(n) = n*c + first 
    return first + n * c;
  }
}
