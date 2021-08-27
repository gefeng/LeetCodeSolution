package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Array Nesting",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/array-nesting/"
)
public class Q565 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int arrayNesting(int[] nums) {
        int n = nums.length;
        int res = 0;
        boolean[] visited = new boolean[n];

        for(int i = 0; i < n; i++) {
            if(visited[i]) {
                continue;
            }

            int next = i;
            int cnt = 0;
            while(!visited[next]) {
                visited[next] = true;
                next = nums[next];
                cnt++;
            }

            res = Math.max(res, cnt);
        }

        return res;
    }
}
