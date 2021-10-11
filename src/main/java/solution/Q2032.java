package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Two Out of Three",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/two-out-of-three/"
)
public class Q2032 {
    /**
     * Time:  O(M + N + K)
     * Space: O(M + N + K)
     * */
    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        List<Integer> ans = new ArrayList<>();
        boolean[] seen1 = new boolean[101];
        boolean[] seen2 = new boolean[101];
        boolean[] seen3 = new boolean[101];

        for(int num : nums1) {
            seen1[num] = true;
        }

        for(int num : nums2) {
            seen2[num] = true;
        }

        for(int num : nums3) {
            seen3[num] = true;
        }

        for(int i = 1; i <= 100; i++) {
            int cnt = 0;
            if(seen1[i]) {
                cnt++;
            }
            if(seen2[i]) {
                cnt++;
            }
            if(seen3[i]) {
                cnt++;
            }

            if(cnt >= 2) {
                ans.add(i);
            }
        }

        return ans;
    }
}
