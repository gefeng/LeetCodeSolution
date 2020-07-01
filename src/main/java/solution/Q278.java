package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "First Bad Version",
        difficulty = QDifficulty.EASY,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/first-bad-version/"
)
public class Q278 {
    boolean isBadVersion(int version) {
        return true;
    }

    public int firstBadVersion(int n) {
        int left = 0;
        int right = n;
        int middle = 0;
        while(left < right) {
            middle = left + (right - left) / 2;
            if(isBadVersion(middle)) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }

        return left;
    }
}
