package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Queue;

@Problem(
        title = "Minimum Possible Integer After at Most K Adjacent Swaps On Digits",
        difficulty = QDifficulty.HARD,
        tag = QTag.BINARY_INDEXED_TREE,
        url = "https://leetcode.com/problems/minimum-possible-integer-after-at-most-k-adjacent-swaps-on-digits/"
)
public class Q1505 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    private class BIT {
        int[] bit;
        BIT(int n) {
            bit = new int[n + 1];
        }

        void add(int i, int val) {
            for(; i < bit.length; i += i & (-i)) {
                bit[i] += val;
            }
        }

        int query(int i) {
            int res = 0;
            for(; i > 0; i -= i & (-i)) {
                res += bit[i];
            }
            return res;
        }
    }
    public String minInteger(String num, int k) {
        int n = num.length();
        BIT bit = new BIT(n + 1);
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < n; i++) {
            bit.add(i + 1, 1);
        }

        Queue<Integer>[] map = new Queue[10];
        for(int i = 0; i < 10; i++) {
            map[i] = new ArrayDeque<>();
        }

        for(int i = 0; i < n; i++) {
            map[num.charAt(i) - '0'].offer(i);
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < 10; j++) {
                Queue<Integer> queue = map[j];

                if(!queue.isEmpty()) {
                    int pos = queue.peek();
                    int steps = bit.query(pos);

                    if(steps <= k) {
                        sb.append((char)(j + '0'));
                        k -= steps;

                        bit.add(pos + 1, -1);
                        queue.poll();
                        break;
                    }
                }
            }
        }

        return sb.toString();
    }
}
