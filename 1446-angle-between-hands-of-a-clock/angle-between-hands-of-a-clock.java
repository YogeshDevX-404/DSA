class Solution {
    public double angleClock(int hour, int minutes) {
        // 1. Calculate the position of the minute hand in degrees
        double minuteAngle = minutes * 6;
        
        // 2. Calculate the position of the hour hand in degrees
        // (hour % 12) handles the 12 o'clock position moving back to 0 degrees base
        double hourAngle = (hour % 12) * 30 + minutes * 0.5;
        
        // 3. Find the absolute difference
        double diff = Math.abs(hourAngle - minuteAngle);
        
        // 4. Return the smaller angle
        return Math.min(diff, 360 - diff);
    }
}