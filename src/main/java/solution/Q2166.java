package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Design Bitset",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DESIGN,
        url = "https://leetcode.com/problems/design-bitset/"
)
public class Q2166 {
    /**
     * Time:  O(1)
     * Space: O(N)
     * */
    int n;
    int[] bits;
    int[] rbits;
    int cnt;
    public Q2166(int size) {
        this.n = size;
        this.bits = new int[n];
        this.rbits = new int[n];
        this.cnt = 0;

        Arrays.fill(rbits, 1);
    }

    public void fix(int idx) {
        if(bits[idx] == 1) return;
        bits[idx] = 1;
        rbits[idx] = 0;
        cnt++;
    }

    public void unfix(int idx) {
        if(bits[idx] == 0) return;
        bits[idx] = 0;
        rbits[idx] = 1;
        cnt--;
    }

    public void flip() {
        int[] temp = bits;
        bits = rbits;
        rbits = temp;

        cnt = n - cnt;
    }

    public boolean all() {
        return cnt == n;
    }

    public boolean one() {
        return cnt > 0;
    }

    public int count() {
        return cnt;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            sb.append(bits[i]);
        }
        return sb.toString();
    }
}
