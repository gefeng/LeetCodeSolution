package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Find Unique Binary String",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/find-unique-binary-string/"
)
public class Q1980 {
    /**
     * Time:  O(N)
     * Space: O(2 ^ N)
     * */
    public String findDifferentBinaryString(String[] nums) {
        int n = nums.length;
        boolean[] map = new boolean[1 << n];

        for(String s : nums) {
            int num = 0;
            for(int i = 0; i < n; i++) {
                num = (num << 1) + s.charAt(i) - '0';
            }
            map[num] = true;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < map.length; i++) {
            if(!map[i]) {
                for(int j = 0; j < n; j++) {
                    sb.append(i % 2);
                    i = i / 2;
                }
                break;
            }
        }

        return sb.reverse().toString();
    }
}
