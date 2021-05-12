package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Russian Doll Envelopes",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/russian-doll-envelopes/"
)
public class Q354 {
    /*
        state: dp[i] is the maximum number of envelopes can russian doll end with envelop[i]
        transition: dp[i] = max(dp[j] + 1) for each j where envelopes[i] > envelopes[j]
    */
    public int maxEnvelopes(int[][] envelopes) {
        //return dpSolution(envelopes);
        return binarySearchSolution(envelopes);
    }

    private int dpSolution(int[][] envelopes) {
        Arrays.sort(envelopes, (a, b) -> Integer.compare(a[0], b[0]));

        int n = envelopes.length;
        int[] dp = new int[n];
        int max = 1;

        for(int i = 0; i < n; i++) {
            dp[i] = 1;
            for(int j = 0; j < i; j++) {
                if(envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            max = Math.max(max, dp[i]);
        }

        return max;
    }

    /*
        fix w by sorting
        find longest increasing subsequence in h
        what if two envelopes have equal width?
        ascending order on width and descending order on height if equal width
        i.e. [1,5][1,6][1,7]
        after sorting [1,7][1,6][1,5] 7,6,5 are excluded from LIS

        1,4,6   3
    */
    private int binarySearchSolution(int[][] envelopes) {
        Arrays.sort(envelopes, (a, b) -> {
            if(a[0] == b[0]) {
                return Integer.compare(b[1], a[1]);
            }
            return Integer.compare(a[0], b[0]);
        });

        int n = envelopes.length;
        int[] h = new int[n];
        for(int i = 0; i < n; i++) {
            h[i] = envelopes[i][1];
        }

        int[] lis = new int[n];
        lis[0] = h[0];
        int maxLen = 1;
        for(int i = 1; i < n; i++) {
            int pos = binarySearch(lis, maxLen, h[i]);
            if(pos == -1) {
                if(h[i] < lis[0]) {
                    lis[0] = h[i];
                } else {
                    maxLen++;
                    lis[maxLen - 1] = h[i];
                }
            } else {
                lis[pos] = h[i];
            }
        }

        return maxLen;
    }

    private int binarySearch(int[] lis, int len, int target) {
        int idx = -1;
        int lo = 0;
        int hi = len - 1;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if(lis[mid] >= target) {
                idx = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return idx;
    }
}
