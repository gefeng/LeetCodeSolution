package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Max Chunks To Make Sorted II",
        difficulty = QDifficulty.HARD,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/max-chunks-to-make-sorted-ii/"
)
public class Q768 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(1)
     * */
    public int maxChunksToSorted(int[] arr) {
        int n = arr.length;
        int ans = 0;

        int max = -1;
        for(int i = 0; i < n; i++) {
            max = Math.max(max, arr[i]);

            int min = Integer.MAX_VALUE;
            for(int j = i + 1; j < n; j++) {
                min = Math.min(min, arr[j]);
            }

            if(min >= max) {
                ans++;
                max = 0;
            }
        }

        return ans;
    }
}
