package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count Good Triplets",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/count-good-triplets/"
)
public class Q1534 {
    /**
     * Time:  O(N ^ 3)
     * Space: O(1)
     * */
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int cnt = 0;
        int n = arr.length;

        for(int i = 0; i < n - 2; i++) {
            for(int j = i + 1; j < n - 1; j++) {
                if(Math.abs(arr[i] - arr[j]) > a) {
                    continue;
                }
                for(int k = j + 1; k < n; k++) {
                    if(Math.abs(arr[j] - arr[k]) <= b && Math.abs(arr[i] - arr[k]) <= c) {
                        cnt++;
                    }
                }
            }
        }

        return cnt;
    }
}
