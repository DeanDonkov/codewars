//kata: https://www.codewars.com/kata/56445c4755d0e45b8c00010a/ [6kyu]

public class BankerPlan {
    
    public static boolean fortune(int f0, double p, int c0, int n, double i) {
        int prev_x = f0; int prev_c = c0;
        int nou_x = -1; int nou_c = -1;
        for (int k = 1; k < n; k++) {
            nou_x = (int)Math.floor(prev_x + p/100.0 * prev_x - prev_c);
            nou_c = (int)Math.floor(prev_c + i/100.0 * prev_c);
            prev_x = nou_x; prev_c = nou_c;
        }
        return (nou_x >= 0);
    }
}
