package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.TreeMap;

@Problem(
        title = "Advantage Shuffle",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/advantage-shuffle/"
)
public class Q870 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] ans = new int[n];
        TreeMap<Integer, Integer> map = new TreeMap<>();

        Arrays.fill(ans, -1);

        for(int x : nums1) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }

        for(int i = 0; i < n; i++) {
            int t = nums2[i] + 1;

            Integer c = map.ceilingKey(t);
            if(c != null) {
                ans[i] = c;

                int left = map.get(c);
                if(left == 1) {
                    map.remove(c);
                } else {
                    map.put(c, left - 1);
                }
            }
        }

        for(int i = 0; i < n; i++) {
            if(ans[i] == -1) {
                Integer k = map.firstKey();
                ans[i] = k;

                int left = map.get(k);
                if(left == 1) {
                    map.remove(k);
                } else {
                    map.put(k, left - 1);
                }
            }
        }

        return ans;
    }
}
