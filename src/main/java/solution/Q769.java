package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Max Chunks To Make Sorted",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/max-chunks-to-make-sorted/"
)
public class Q769 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int maxChunksToSorted(int[] arr) {
        int n = arr.length;
        int ans = 0;
        int max = 0;
        for(int i = 0; i < n; i++) {
            max = Math.max(max, arr[i]);
            if(max == i) ans++;
        }

        return ans;
    }
}
