// LeetCode 1344. Angle Between Hands of a Clock
// Given the current hour and minute, return the smaller of the two angles created by the hands of the clock.
// Intuition: Determine the angles created by the minute and hour hands
// Hour: 30 deg for each hour, and an extra 0.5 deg for each minute
// Minute: 6 deg per minute
// Return the smaller of the two angles
// O(1) runtime: a constant amount of operations are performed no matter what number is passed
// O(1) space: two variables are used to hold the angle values

public class AngleBetweenClockHands {

    public double angleClockClean(int hour, int minutes) {
        double h = 360.0 * (hour + minutes/60.0)/12;
        double m = 360.0 * minutes/60.0;

        double ans = Math.abs(h - m);
        return (ans > 180) ? 360 - ans : ans;
    }

    public double angleClock(int hour, int minutes) {
        double h = 30 * hour + (0.5 * minutes);
        double m = 6 * minutes;

        return (m > h) ? Math.min(m - h, 360-(m-h)) : Math.min(h - m, 360-(h-m));
    }
}
