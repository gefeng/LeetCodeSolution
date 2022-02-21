package solution.biweekly72;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count Good Triplets in an Array",
        difficulty = QDifficulty.HARD,
        tag = QTag.BINARY_INDEXED_TREE,
        url = "https://leetcode.com/contest/biweekly-contest-72/problems/count-good-triplets-in-an-array/"
)
public class Q2179 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public long goodTriplets(int[] nums1, int[] nums2) {
        long ans = 0;
        int n = nums1.length;
        int[] pos = new int[n];
        BIT bit = new BIT(n);

        for(int i = 0; i < n; i++) {
            pos[nums1[i]] = i;
        }

        for(int i = 0; i < n; i++) {
            int p = pos[nums2[i]]; // position in nums1
            int l = bit.query(p);
            int r = bit.query(p, n - 1);

            ans += (long)l * (n - p - 1 - r);

            bit.update(p, 1);
        }

        return ans;
    }

    private class BIT {
        int[] bit;
        int n;
        BIT(int n) {
            this.n = n + 1;
            this.bit = new int[this.n];
        }

        void update(int i, int val) {
            i++;
            for(; i < n; i += i & (-i)) {
                bit[i] += val;
            }
        }

        int query(int i) {
            int res = 0;
            i++;
            for(; i > 0; i -= i & (-i)) {
                res += bit[i];
            }
            return res;
        }

        int query(int i, int j) {
            return query(j) - query(i - 1);
        }
    }
}
