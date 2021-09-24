package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Build an Array With Stack Operations",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/build-an-array-with-stack-operations/"
)
public class Q1441 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public List<String> buildArray(int[] target, int n) {
        List<String> ans = new ArrayList<>();

        int prev = 0;
        for(int i = 0; i < target.length; i++) {
            int cnt = target[i] - prev - 1;
            for(int j = 0; j < cnt; j++) {
                ans.add("Push");
                ans.add("Pop");
            }
            ans.add("Push");
            prev = target[i];
        }

        return ans;
    }
}
