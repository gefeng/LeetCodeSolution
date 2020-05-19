package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count Binary Substrings",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/count-binary-substrings/"
)
public class Q696 {
    public int countBinarySubstrings(String s) {
        int[] groups = new int[s.length()];
        int groupIndex = 0;
        int count = 0;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 || s.charAt(i) == s.charAt(i - 1))
                groups[groupIndex]++;
            else
                groups[++groupIndex]++;
        }

        for(int i = 0; i < groupIndex; i++) {
            count += Math.min(groups[i], groups[i+1]);
        }
        return count;
    }
}
