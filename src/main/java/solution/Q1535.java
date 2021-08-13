package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Find the Winner of an Array Game",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/find-the-winner-of-an-array-game/"
)
public class Q1535 {
    /**
     * Time:  O(N)
     * Space: O(1)
     *
     * */
    public int getWinner(int[] arr, int k) {
        int n = arr.length;
        int preWin = arr[0];
        int cnt = 0;

        for(int i = 1; i < n; i++) {
            int win = Math.max(preWin, arr[i]);
            if(win == preWin) {
                cnt++;
            } else {
                cnt = 1;
            }

            preWin = win;

            if(cnt == k) {
                break;
            }
        }

        return preWin;
    }
}
