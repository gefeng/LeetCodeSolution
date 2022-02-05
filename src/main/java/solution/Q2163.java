package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Minimum Difference in Sums After Removal of Elements",
        difficulty = QDifficulty.HARD,
        tag = QTag.HEAP,
        url = "https://leetcode.com/problems/minimum-difference-in-sums-after-removal-of-elements/"
)
public class Q2163 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public long minimumDifference(int[] nums) {
        int n = nums.length / 3;
        long ans = Long.MAX_VALUE;
        long sum1 = 0L;
        long sum2 = 0L;
        Queue<Integer> pqL = new PriorityQueue<>(Comparator.reverseOrder());

        HashMap<Integer, Integer> map1 = new HashMap<>();
        TreeMap<Integer, Integer> map2 = new TreeMap<>();

        for(int i = 0; i < n; i++) {
            sum1 += nums[i];
            pqL.offer(nums[i]);
        }

        for(int i = n; i < 3 * n; i++) {
            int x = nums[i];
            map2.put(x, map2.getOrDefault(x, 0) + 1);
        }

        for(int i = 0; i < n; i++) {
            int max = map2.lastKey();
            sum2 += max;
            map1.put(max, map1.getOrDefault(max, 0) + 1);
            removeFromMap(map2, max);
        }

        ans = sum1 - sum2;
        for(int i = n; i < 2 * n; i++) {
            int x = nums[i];
            sum1 += x;
            pqL.offer(x);
            sum1 -= pqL.poll();

            if(map1.containsKey(x)) {
                removeFromMap(map1, x);
                sum2 -= x;
                int max = map2.lastKey();
                sum2 += max;
                map1.put(max, map1.getOrDefault(max, 0) + 1);
                removeFromMap(map2, max);
            } else {
                removeFromMap(map2, x);
            }

            ans = Math.min(ans, sum1 - sum2);
        }

        return ans;
    }

    private void removeFromMap(Map<Integer, Integer> map, int x) {
        int f = map.get(x);
        if(f == 1) map.remove(x);
        else map.put(x, f - 1);
    }
}
