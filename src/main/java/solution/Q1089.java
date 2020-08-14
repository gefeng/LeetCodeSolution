package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Duplicate Zeros",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/duplicate-zeros/"
)
public class Q1089 {
    public void duplicateZeros(int[] arr) {
        int l = 0;
        int r = arr.length - 1;
        boolean dupLastZero = false;
        while(l < r) {
            if(arr[l] == 0) {
                r--;
                if(l == r)
                    dupLastZero = true;
            }
            l++;
        }

        int shift = arr.length - 1 - r;
        while(shift != 0) {
            arr[r + shift] = arr[r];
            if(arr[r] == 0 && (r + shift != arr.length - 1 || dupLastZero)) {
                shift--;
                arr[r + shift] = 0;
            }
            r--;
        }
    }
}
