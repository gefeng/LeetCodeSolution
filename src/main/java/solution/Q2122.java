package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Recover the Original Array",
        difficulty = QDifficulty.HARD,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/recover-the-original-array/"
)
public class Q2122 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    public int[] recoverArray(int[] nums) {
        int m = nums.length;
        int n = m / 2;
        int[] ans = new int[n];
        Arrays.sort(nums);

        for(int i = 1; i < m; i++) {
            if(nums[i] == nums[0] || (nums[i] - nums[0]) % 2 != 0) continue;
            int k = (nums[i] - nums[0]) / 2;

            Map<Integer, Integer> map = new HashMap<>();
            List<Integer> cand = new ArrayList<>();

            for(int j = 0; j < m; j++) {
                if(map.isEmpty()) {
                    map.put(nums[j], 1);
                } else {
                    int lo = nums[j] - 2 * k;
                    if(lo <= 0 || !map.containsKey(lo)) {
                        map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);
                    } else {   // find a pair
                        int cnt = map.get(lo);
                        if(cnt == 1) map.remove(lo);
                        else map.put(lo, cnt - 1);
                        cand.add(nums[j] - k);
                    }
                }
            }

            if(map.isEmpty()) {
                ans = cand.stream().mapToInt(a -> a.intValue()).toArray();
                break;
            }
        }

        return ans;
    }
}
