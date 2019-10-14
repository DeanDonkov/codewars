class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        boolean collisionHasOccurred = true;
        
        while (collisionHasOccurred) {
            collisionHasOccurred = false;
            int numberOfAsteroidsRemoved = 0;
            for (int i = 0; i < asteroids.length-1; i++) {
                int current = asteroids[i];
                int next    = asteroids[i+1];
                
                if (movingTowardsEachOther(current, next)) {
                    collisionHasOccurred = true;
                    if (sizeIsEqual(current, next)) {
                        asteroids[i]   = 0;
                        asteroids[i+1] = 0;
                        numberOfAsteroidsRemoved += 2;
                    } else {
                        if (currentIsLarger(current, next)) 
                            asteroids[i+1] = 0;
                        else 
                            asteroids[i] = 0;
                        numberOfAsteroidsRemoved++;
                    }
                }
            }
            int[] temp = new int[asteroids.length - numberOfAsteroidsRemoved];
            int tempIndex = 0;
            for (int i : asteroids) {
                if (i != 0) {
                    temp[tempIndex] = i;
                    tempIndex++;
                }   
            }
            asteroids = temp;
        }
        return asteroids;
    }
    private boolean movingTowardsEachOther(int current, int next) {
        return current > 0 && next < 0;
    }
    private boolean sizeIsEqual(int current, int next) {
        return current + next == 0;
    }
    private boolean currentIsLarger(int current, int next) {
        return current + next > 0;
    }
}
