import java.util.Arrays;

class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        // Asteroids ko ascending order mein sort karenge
        Arrays.sort(asteroids);
        
        // Planet ki mass ko track karne ke liye long ka use kiya hai 
        // taaki bade numbers hone par integer overflow na ho
        long currentMass = mass;
        
        // Ekdum standard for loop index ke sath
        for (int i = 0; i < asteroids.length; i++) {
            int currentAsteroid = asteroids[i];
            
            // Agar planet ki mass current asteroid se kam hai, toh planet destroy ho jayega
            if (currentMass < currentAsteroid) {
                return false;
            }
            
            // Nahi toh planet asteroid ko absorb karke uski mass gain karega
            currentMass += currentAsteroid;
        }
        
        return true;
    }
}