package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Choose Numbers From Two Arrays in Range",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/choose-numbers-from-two-arrays-in-range/"
)
public class Q2143 {
    /**
     * Time:  O(N * max(sum))
     * Space: O(max(sum))
     * */
    public int countSubranges(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int mod = (int)1e9 + 7;
        int ans = 0;
        Map<Integer, Integer> rs = new HashMap<>();

        for(int i = 0; i < n; i++) {
            Map<Integer, Integer> nrs = new HashMap<>();

            nrs.put(nums1[i], 1);
            nrs.put(-nums2[i], nrs.getOrDefault(-nums2[i], 0) + 1);

            for(int sum : rs.keySet()) {
                int f = rs.get(sum);
                int nsum1 = sum + nums1[i];
                int nsum2 = sum - nums2[i];

                nrs.put(nsum1, (nrs.getOrDefault(nsum1, 0) + f) % mod);
                nrs.put(nsum2, (nrs.getOrDefault(nsum2, 0) + f) % mod);
            }

            ans = (ans + nrs.getOrDefault(0, 0)) % mod;
            rs = nrs;
        }

        return ans;
    }
}
