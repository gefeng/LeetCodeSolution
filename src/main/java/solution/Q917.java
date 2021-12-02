package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Reverse Only Letters",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/reverse-only-letters/"
)
public class Q917 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public String reverseOnlyLetters(String s) {
        char[] arr = s.toCharArray();
        int n = s.length();
        int l = 0;
        int r = n - 1;
        while(l < r) {
            if(!Character.isLetter(arr[l])) {
                l++;
            } else if(!Character.isLetter(arr[r])) {
                r--;
            } else {
                char c = arr[l];
                arr[l] = arr[r];
                arr[r] = c;
                l++;
                r--;
            }
        }

        return new String(arr);
    }
}
