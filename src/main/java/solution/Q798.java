package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Smallest Rotation with Highest Score",
        difficulty = QDifficulty.HARD,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/smallest-rotation-with-highest-score/"
)
public class Q798 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int bestRotation(int[] nums) {
        int n = nums.length;

        List<int[]> bad = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            int v = nums[i];
            if(v == 0) continue;
            if(v > n - 1) {
                bad.add(new int[] {0, n - 1});
            } else {
                if(i + 1 >= v) {
                    bad.add(new int[] {i - v + 1, i});
                } else {
                    bad.add(new int[] {0, i});
                    bad.add(new int[] {n - v + i + 1, n - 1});
                }
            }
        }

        int[] freq = new int[n];

        for(int[] r : bad) {
            freq[r[0]]++;
            if(r[1] + 1 < freq.length) {
                freq[r[1] + 1]--;
            }
        }

        int k = 0;
        int tot = 0;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < freq.length; i++) {
            tot += freq[i];
            if(tot < min) {
                min = tot;
                k = i;
            }
        }

        return k;
    }
}
