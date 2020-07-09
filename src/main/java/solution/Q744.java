package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Find Smallest Letter Greater Than Target",
        difficulty = QDifficulty.EASY,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/find-smallest-letter-greater-than-target/"
)
public class Q744 {
    public char nextGreatestLetter(char[] letters, char target) {
        if(target >= letters[letters.length - 1])
            return letters[0];
        int left = 0;
        int right = letters.length - 1;
        int mid = 0;
        while(left < right) {
            mid = left + (right - left) / 2;
            if(letters[mid] <= target)
                left = mid + 1;
            else
                right = mid;
        }
        return letters[left];
    }
}
