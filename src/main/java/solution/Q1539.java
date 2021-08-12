package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Kth Missing Positive Number",
        difficulty = QDifficulty.EASY,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/kth-missing-positive-number/"
)
public class Q1539 {
    public int findKthPositive(int[] arr, int k) {
        return binarySearchSol(arr, k);
    }

    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    private int linearLookupSol(int[] arr, int k) {
        int res = 0;
        boolean[] nums = new boolean[2001];
        for(int num : arr) {
            nums[num] = true;
        }

        int cnt = 0;
        for(int i = 1; i < 2001; i++) {
            if(!nums[i]) {
                cnt++;
            }
            if(cnt == k) {
                res = i;
                break;
            }
        }

        return res;
    }

    /**
     * Time:  O(logN)
     * Space: O(1)
     * */
    private int binarySearchSol(int[] arr, int k) {
        int n = arr.length;
        int lo = 1;
        int hi = n;
        int res = 0;

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            int num = arr[mid - 1];
            if(num - mid >= k) {
                hi = mid - 1;
            } else {
                res = mid;
                lo = mid + 1;
            }
        }

        return res == 0 ? k : k - (arr[res - 1] - res) + arr[res - 1];
    }
}
