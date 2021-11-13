package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Previous Permutation With One Swap",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/previous-permutation-with-one-swap/"
)
public class Q1053 {
    public int[] prevPermOpt1(int[] arr) {
        int n = arr.length;

        int p1 = -1;
        for(int i = n - 2; i >= 0; i--) {
            if(arr[i] > arr[i + 1]) {
                p1 = i;
                break;
            }
        }

        if(p1 == -1) {
            return arr;
        }

        int p2 = n - 1;
        for(int i = n - 1; i >= 0; i--) {
            if(arr[i] < arr[p1] && arr[i] != arr[i - 1]) {
                p2 = i;
                break;
            }
        }

        int temp = arr[p1];
        arr[p1] = arr[p2];
        arr[p2] = temp;

        return arr;
    }
}
