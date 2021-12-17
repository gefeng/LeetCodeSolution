package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Longest Mountain in Array",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TWO_POINTERS,
        url = "https://leetcode.com/problems/longest-mountain-in-array/"
)
public class Q845 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int longestMountain(int[] arr) {
        int n = arr.length;
        int ans = 0;

        if(n < 3) return 0;

        int cnt = 0;
        int l = 0;
        int r = 1;
        while(r < n) {
            boolean up = false;
            while(r < n - 1 && arr[r] > arr[r - 1]) {
                r++;
                up = true;
            }

            if(!up) {
                l = r;
                r++;
                continue;
            }

            boolean down = false;
            while(r < n && arr[r] < arr[r - 1]) {
                r++;
                down = true;
            }

            if(down) {
                ans = Math.max(ans, r - l);
            }

            l = r - 1;
        }

        return ans;
    }
}
