package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Next Closest Time",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/next-closest-time/"
)
public class Q681 {
    /**
     * Time:  O(4 ^ 4)
     * Space: O(1)
     * */
    String ans = "";
    int origin = 0;
    int best = 0;
    public String nextClosestTime(String time) {
        int[] digits = new int[4];
        for(int i = 0; i < 4; i++) {
            digits[i] = i > 1 ? time.charAt(i + 1) - '0' : time.charAt(i) - '0';
        }

        origin = toMin(digits);

        gen(digits, 0, new int[4]);

        return ans.isEmpty() ? time : ans;
    }

    private void gen(int[] digits, int i, int[] time) {
        if(i == 4) {
            if(isValid(time)) {
                int min = toMin(time);
                if(min == origin) return;

                min = min > origin ? min : min + 24 * 60;

                if(ans.isEmpty()) {
                    best = min;
                    ans = toString(time);
                } else {
                    if(best - origin > min - origin) {
                        best = min;
                        ans = toString(time);
                    }
                }
            }
            return;
        }

        for(int d : digits) {
            time[i] = d;
            gen(digits, i + 1, time);
        }
    }

    private int toMin(int[] time) {
        int hour = time[0] * 10 + time[1];
        int min = time[2] * 10 + time[3];
        return hour * 60 + min;
    }

    private boolean isValid(int[] time) {
        if(time[0] > 2) return false;
        if(time[0] == 2 && time[1] > 3) return false;
        if(time[2] > 5) return false;
        return true;
    }

    private String toString(int[] time) {
        int hour = time[0] * 10 + time[1];
        int min = time[2] * 10 + time[3];
        return String.format("%02d:%02d", hour, min);
    }
}
