package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.TreeMap;

@Problem(
        title = "Odd Even Jump",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/odd-even-jump/"
)
public class Q975 {
    /**
     * Time:  O(N * logN)
     * Space: O()
     * */
    public int oddEvenJumps(int[] arr) {
        int ans = 0;
        int n = arr.length;
        boolean[][] dp = new boolean[n][2];

        dp[n - 1][0] = true;
        dp[n - 1][1] = true;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(arr[n - 1], n - 1);

        for(int i = n - 2; i >= 0; i--) {
            int v = arr[i];

            Integer c = map.ceilingKey(v);
            Integer f = map.floorKey(v);

            if(c == null) {
                dp[i][0] = false;
            } else {
                dp[i][0] = dp[map.get(c)][1];
            }

            if(f == null) {
                dp[i][1] = false;
            } else {
                dp[i][1] = dp[map.get(f)][0];
            }

            map.put(v, i);
        }

        for(int i = 0; i < n; i++) {
            ans += dp[i][0] ? 1 : 0;
        }

        return ans;
    }
}
