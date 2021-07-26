package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Problem(
        title = "Sort Array by Increasing Frequency",
        difficulty = QDifficulty.EASY,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/sort-array-by-increasing-frequency/"
)
public class Q1636 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public int[] frequencySort(int[] nums) {
        int n = nums.length;
        List<int[]> sorted = new ArrayList<>();
        int[] cnt = new int[201];
        int[] res = new int[n];
        for(int i = 0; i < n; i++) {
            cnt[nums[i] + 100]++;
        }

        for(int i = 0; i < 201; i++) {
            if(cnt[i] == 0) {
                continue;
            }

            sorted.add(new int[] {i - 100, cnt[i]});
        }

        Collections.sort(sorted, (a, b) -> {
            if(a[1] == b[1]) {
                return Integer.compare(b[0], a[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        int i = 0;
        for(int[] p : sorted) {
            int freq = p[1];
            while(freq != 0) {
                res[i++] = p[0];
                freq--;
            }
        }

        return res;
    }
}
