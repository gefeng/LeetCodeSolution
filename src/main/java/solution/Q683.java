package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "K Empty Slots",
        difficulty = QDifficulty.HARD,
        tag = QTag.BINARY_INDEXED_TREE,
        url = "https://leetcode.com/problems/k-empty-slots/"
)
public class Q683 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public int kEmptySlots(int[] bulbs, int k) {
        int n = bulbs.length;
        int ans = -1;
        BIT bit = new BIT(n);

        for(int i = 1; i <= n; i++) {
            int b = bulbs[i - 1] - 1;
            bit.update(b);

            if(i >= 2) {
                // oxxx o turn on the one on the right
                if(b >= k + 1 && bit.query(b - k, b - 1) == 0 && bit.query(b - k - 1, b - k - 1) == 1) {
                    ans = i;
                    break;
                }
                // o xxxo turn on the one on the left
                if(b < n - k - 1 && bit.query(b + 1, b + k) == 0 && bit.query(b + k + 1, b + k + 1) == 1) {
                    ans = i;
                    break;
                }
            }
        }

        return ans;
    }

    // one based fenwick tree
    private class BIT {
        int[] x;
        int n;
        BIT(int n) {
            this.n = n + 1;
            this.x = new int[n + 1];
        }

        void update(int i) {
            i++;
            while(i < n) {
                x[i] += 1;
                i += i & (-i);
            }
        }

        int query(int i) {
            i++;
            int sum = 0;
            while(i > 0) {
                sum += x[i];
                i -= i & (-i);
            }
            return sum;
        }

        int query(int i, int j) {
            return query(j) - query(i - 1);
        }
    }
}
