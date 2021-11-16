package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Moving Stones Until Consecutive",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/moving-stones-until-consecutive/"
)
public class Q1033 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public int[] numMovesStones(int a, int b, int c) {
        int[] arr = new int[] {a, b, c};
        Arrays.sort(arr);
        int min = 0;

        if(arr[1] - arr[0] > 2 && arr[2] - arr[1] > 2) {
            min = 2;
        } else if(arr[1] - arr[0] == 1 && arr[2] - arr[1] == 1) {
            min = 0;
        } else {
            min = 1;
        }

        int max = arr[1] - arr[0] - 1 + arr[2] - arr[1] - 1;

        return new int[] {min, max};
    }
}
