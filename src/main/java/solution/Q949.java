package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Largest Time for Given Digits",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/largest-time-for-given-digits/"
)
public class Q949 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public String largestTimeFromDigits(int[] arr) {
        int best = -1;
        String ans = "";
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                for(int k = 0; k < 4; k++) {
                    for(int l = 0; l < 4; l++) {
                        if(i != j && i != k && i != l && j != k && j != l && k != l) {
                            int h = arr[i] * 10 + arr[j];
                            int m = arr[k] * 10 + arr[l];
                            if(isOk(h, m)) {
                                int min = h * 60 + m;
                                if(min > best) {
                                    best = min;
                                    ans = String.format("%02d:%02d", h, m);
                                }
                            }
                        }
                    }
                }
            }
        }

        return ans;
    }

    private boolean isOk(int h, int m) {
        return h >= 0 && h < 24 && m >= 0 && m < 60;
    }
}
