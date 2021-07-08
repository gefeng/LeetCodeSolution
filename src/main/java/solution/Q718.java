package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Maximum Length of Repeated Subarray",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ROLLING_HASH,
        url = "https://leetcode.com/problems/maximum-length-of-repeated-subarray/"
)
public class Q718 {
    private static final long BASE = 101;
    private static final long MOD = (long)1e12 + 7;
    public int findLength(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int lo = 0;
        int hi = Math.min(m, n);
        int ans = 0;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if(hasCommon(nums1, nums2, mid)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }

    private boolean hasCommon(int[] nums1, int[] nums2, int len) {
        int m = nums1.length;
        int n = nums2.length;
        Set<Long> hashSet = new HashSet<>();

        long hash = 0;
        long d = 1;
        for(int l = 0, r = 0; r < m; r++) {
            hash = ((hash * BASE) % MOD + nums1[r]) % MOD;

            if(r - l + 1 > len) {
                hash = (MOD + hash - (nums1[l++] * d) % MOD) % MOD;
            } else {
                d = (d * BASE) % MOD;
            }

            if(r - l + 1 == len) {
                hashSet.add(hash);
            }
        }

        hash = 0;
        d = 1;
        for(int l = 0, r = 0; r < n; r++) {
            hash = ((hash * BASE) % MOD + nums2[r]) % MOD;

            if(r - l + 1 > len) {
                hash = (MOD + hash - (nums2[l++] * d) % MOD) % MOD;
            } else {
                d = (d * BASE) % MOD;
            }

            if(r - l + 1 == len && hashSet.contains(hash)) {
                return true;
            }
        }
        return false;
    }
}
