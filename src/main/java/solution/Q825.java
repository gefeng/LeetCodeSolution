package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Friends Of Appropriate Ages",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/friends-of-appropriate-ages/"
)
public class Q825 {
    /**
     * Time:  O(N * logN)
     * Space: O(logN)
     * */
    public int numFriendRequests(int[] ages) {
        int n = ages.length;
        int ans = 0;

        Arrays.sort(ages);

        for(int i = 0; i < n; i++) {
            int x = ages[i];

            if(x < 15) continue;

            int ub = upperBound(ages, x / 2 + 7);
            int lb = lowerBound(ages, x);

            if(ub != -1 && lb >= ub) {
                ans += lb - ub;
            }
        }

        return ans;
    }

    private int upperBound(int[] arr, int t) {
        int lo = 0;
        int hi = arr.length - 1;
        int idx = -1;
        while(lo <= hi) {
            int mid = lo + hi >> 1;
            if(arr[mid] > t) {
                idx = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return idx;
    }

    private int lowerBound(int[] arr, int t) {
        int lo = 0;
        int hi = arr.length - 1;
        int idx = -1;
        while(lo <= hi) {
            int mid = lo + hi >> 1;
            if(arr[mid] <= t) {
                idx = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return idx;
    }
}
