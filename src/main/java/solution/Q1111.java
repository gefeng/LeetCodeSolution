package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Nesting Depth of Two Valid Parentheses Strings",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/maximum-nesting-depth-of-two-valid-parentheses-strings/"
)
public class Q1111 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] ans = new int[n];

        int max = 0;
        int cnt = 0;
        for(int i = 0; i < n; i++) {
            if(seq.charAt(i) == '(') {
                cnt += 1;
            } else {
                cnt -= 1;
            }
            max = Math.max(max, cnt);
        }

        cnt = 0;
        for(int i = 0; i < n; i++) {
            if(seq.charAt(i) == '(') {
                cnt += 1;
                ans[i] = cnt > max / 2 ? 1 : 0;
            } else {
                ans[i] = cnt > max / 2 ? 1 : 0;
                cnt -= 1;
            }
        }

        return ans;
    }
}
