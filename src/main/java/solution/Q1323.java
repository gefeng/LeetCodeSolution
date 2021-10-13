package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum 69 Number",
        difficulty = QDifficulty.EASY,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/maximum-69-number/"
)
public class Q1323 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int maximum69Number (int num) {
        char[] arr = Integer.toString(num).toCharArray();

        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == '6') {
                arr[i] = '9';
                break;
            }
        }

        return Integer.parseInt(new String(arr));
    }
}
