package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Monotone Increasing Digits",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/monotone-increasing-digits/"
)
public class Q738 {
    /**
     * Time:  O(logN)
     * Space: O(1)
     * */
    public int monotoneIncreasingDigits(int n) {
        char[] arr = Integer.toString(n).toCharArray();

        int j = -1;
        for(int i = 1; i < arr.length; i++) {
            if(arr[i] < arr[i - 1]) {
                j = i;
                break;
            }
        }

        if(j == -1) {
            return n;
        }

        int k = 0;
        for(int i = j - 1; i > 0; i--) {
            if(arr[i] > arr[i - 1]) {
                k = i;
                break;
            }
        }

        arr[k]--;
        for(int i = k + 1; i < arr.length; i++) {
            arr[i] = '9';
        }

        return Integer.parseInt(new String(arr));
    }
}
