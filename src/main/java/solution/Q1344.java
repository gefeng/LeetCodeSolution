package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Angle Between Hands of a Clock",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/angle-between-hands-of-a-clock/"
)
public class Q1344 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public double angleClock(int hour, int minutes) {
        int oneHourAngle = 30;
        int oneMinuteAngle = 6;

        double minuteAngle = minutes * oneMinuteAngle;
        double hourAngle = (hour % 12) * oneHourAngle + (minutes / 60.0) * oneHourAngle;
        double diff = Math.abs(minuteAngle - hourAngle);
        return Math.min(diff, 360 - diff);
    }
}
