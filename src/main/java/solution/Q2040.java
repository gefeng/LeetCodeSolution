package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Kth Smallest Product of Two Sorted Arrays",
        difficulty = QDifficulty.HARD,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/kth-smallest-product-of-two-sorted-arrays/"
)
public class Q2040 {
    /**
     * Time:  O(log(max_prod) * M * log(N))
     * Space: O(1)
     * */
    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        int m = nums1.length;
        int n = nums2.length;

        long lo = (long)-1e10;
        long hi = (long)1e10;
        long ans = 0;

        while(lo <= hi) {
            long mid = hi + lo >> 1;
            long cnt = 0;

            for(int x : nums1) {
                if(x > 0) {
                    cnt += lowerBound(nums2, Math.floorDiv(mid, x) + 1);
                    //cnt += idx == 0 ? 0 : idx + 1;
                } else if(x < 0) {
                    cnt += n - lowerBound(nums2, Math.floorDiv(mid + x + 1, x));
                    //cnt += n - lowerBound(nums2, (mid + x + 1) / x);
                } else {
                    if(mid >= 0) {
                        cnt += n;
                    }
                }
            }
            //System.out.println(mid + " " + cnt);

            if(cnt >= k) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return ans;
    }

    private int lowerBound(int[] arr, long t) {
        int lo = 0;
        int hi = arr.length - 1;
        int ans = 0;

        while(lo <= hi) {
            int mid = lo + hi >> 1;
            if(arr[mid] < t) {
                ans = mid + 1;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }
}
