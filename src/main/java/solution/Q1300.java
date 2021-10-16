package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Sum of Mutated Array Closest to Target",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/sum-of-mutated-array-closest-to-target/"
)
public class Q1300 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public int findBestValue(int[] arr, int target) {
    int n = arr.length;
    int ans = 0;

    Arrays.sort(arr);

    int[] preSum = new int[n + 1];
    for(int i = 1; i <= n; i++) {
        preSum[i] = preSum[i - 1] + arr[i - 1];
    }

    int l = arr[0];
    int r = arr[n - 1];
    int min = Integer.MAX_VALUE;
    for(int i = 0; i <= r; i++) {
        int idx = binarySearch(arr, i);

        int sum = preSum[idx] + (n - idx) * i;

        if(Math.abs(sum - target) < min) {
            ans = i;
            min = Math.abs(sum - target);
        }
    }

    return ans;
}

    private int binarySearch(int[] arr, int t) {
        int lo = 0;
        int hi = arr.length - 1;
        int ans = arr.length;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if(arr[mid] > t) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return ans;
    }
}
