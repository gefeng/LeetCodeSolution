package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Problem(
        title = "Path Sum IV",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/path-sum-iv/"
)
public class Q666 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    Map<Integer, Integer> map = new HashMap<>();
    int ans = 0;
    public int pathSum(int[] nums) {
        for(int x : nums) {
            map.put(x / 10, x % 10);
        }

        dfs(nums[0] / 10, 0);

        return ans;
    }

    private void dfs(int cur, int sum) {
        sum += map.get(cur);

        int l = (cur / 10 + 1) * 10 + (cur % 10) * 2 - 1;
        int r = (cur / 10 + 1) * 10 + (cur % 10) * 2;

        if(!map.containsKey(l) && !map.containsKey(r)) {
            ans += sum;
            return;
        }

        if(map.containsKey(l)) {
            dfs(l, sum);
        }
        if(map.containsKey(r)) {
            dfs(r, sum);
        }
    }
}
