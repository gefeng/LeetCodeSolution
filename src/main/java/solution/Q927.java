package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Three Equals Parts",
        difficulty = QDifficulty.HARD,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/three-equal-parts/"
)
public class Q927 {
    /*
        ..1....1...1..1..1....1..
          i1   j1  i2 j2 i3   j3

         1. each partition should contains equal numbers of 1.
         2. the value of each partition should be equal.
         3. each partition should be able to append # (n - 1 - j3) zeros
    */
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int[] threeEqualParts(int[] arr) {
        int[] ans =  new int[] {-1, -1};
        int n = arr.length;

        int cntOne = 0;
        for(int num : arr) {
            cntOne = num == 1 ? cntOne + 1 : cntOne;
        }

        if(cntOne % 3 != 0) {
            return new int[] {-1, -1};
        }
        if(cntOne == 0) {
            return new int[] {0, 2};
        }
        // 1 3 4 6 7 9
        int total = cntOne;
        cntOne = 0;
        int p1 = 0, p2 = 0, p3 = 0, p4 = 0, p5 = 0, p6 = 0;
        for(int i = 0; i < n; i++) {
            if(arr[i] == 1) {
                cntOne++;
                p1 = cntOne == 1 ? i : p1;
                p2 = cntOne == total / 3 ? i : p2;
                p3 = cntOne == total / 3 + 1 ? i : p3;
                p4 = cntOne == total / 3 * 2 ? i : p4;
                p5 = cntOne == total / 3 * 2 + 1 ? i : p5;
                p6 = cntOne == total ? i : p6;
            }
        }

        int zeroToAppend = n - 1 - p6;

        for(int i = p1, j = p3, k = p5; i <= p2 && j <= p4 && k <= p6; i++, j++, k++) {
            if(arr[i] != arr[j] || arr[i] != arr[k] || arr[j] != arr[k]) {
                return new int[] {-1, -1};
            }
        }

        if(p2 + zeroToAppend >= p3 || p4 + zeroToAppend >= p5) {
            return new int[] {-1, -1};
        }

        return new int[] {p2 + zeroToAppend, p4 + zeroToAppend + 1};
    }
}
