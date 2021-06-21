package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Range Sum Query - Mutable",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_INDEXED_TREE,
        url = "https://leetcode.com/problems/range-sum-query-mutable/"
)
public class Q307 {
    private class BIT {
        int bit[];
        int n;
        BIT(int n) {
            this.n = n;
            this.bit = new int[n + 1];
        }

        void update(int i, int val) {
            for(; i <= n; i += i & (-i) /*equivalent to i & ~x + 1*/) {
                bit[i] += val;
            }
        }

        int query(int i) {
            int sum = 0;
            for(; i > 0; i -= i & (-i)) {
                sum += bit[i];
            }
            return sum;
        }

        int query(int l, int r) {
            return query(r) - query(l - 1);
        }
    }

    private BIT bit;
    private int[] nums;
    public Q307(int[] nums) {
        bit = new BIT(nums.length);
        this.nums = nums;
        for(int i = 0; i < nums.length; i++) {
            bit.update(i + 1, nums[i]);
        }
    }

    public void update(int index, int val) {
        int diff = val - nums[index];
        bit.update(index + 1, diff);
        nums[index] = val;
    }

    public int sumRange(int left, int right) {
        return bit.query(left + 1, right + 1);
    }
}
