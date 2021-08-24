package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Problem(
        title = "Random Flip Matrix",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.RANDOM,
        url = "https://leetcode.com/problems/random-flip-matrix/"
)
public class Q519 {
    /**
     * let's say n is the size of a sequence.
     * 1. There is a 1/n chance to pick a random element.
     * 2. There is a 1/n-1 chance to pick a random element from the rest of the sequence,
     * and the chance for not getting picked in last step is n-1/n, so overall the chance
     * of not get picked last round and get picked this round is n-1/n * 1/n-1 = 1/n
     * We can see the chance are equals by following this approach.
     * */
    int m;
    int n;
    int i;
    Random rand;
    Map<Integer, Integer> picked;
    public Q519(int m, int n) {
        this.m = m;
        this.n = n;
        this.i = m * n - 1;
        this.rand = new Random();
        this.picked = new HashMap<>();
    }

    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public int[] flip() {
        int r = rand.nextInt(i + 1);
        int k = picked.getOrDefault(r, r);

        int last = picked.getOrDefault(i, i);

        picked.put(r, last);
        picked.put(i, k);
        i--;

        return new int[] {k / n, k % n};
    }

    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public void reset() {
        picked = new HashMap();
        i = m * n - 1;
    }
}
