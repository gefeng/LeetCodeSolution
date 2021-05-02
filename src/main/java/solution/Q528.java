package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Random;

@Problem(
        title = "Random Pick with Weight",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/random-pick-with-weight/"
)
public class Q528 {
    /*
    * 一个sampling的问题，结合prefix sum和binary search
    * */
    private static Random rand;
    private int[] prefixSum;
    public Q528(int[] w) {
        prefixSum = new int[w.length];
        prefixSum[0] = w[0];
        for(int i = 1; i < w.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + w[i];
        }

        rand = new Random();
    }

    public int pickIndex() {
        int target = rand.nextInt(prefixSum[prefixSum.length - 1]) + 1;

        int lo = 0;
        int hi = prefixSum.length - 1;
        int mid = 0;
        int idx = 0;
        while(lo <= hi) {
            mid = lo + (hi - lo) / 2;
            if(target == prefixSum[mid]) {
                idx = mid;
                break;
            } else if(target > prefixSum[mid]) {
                lo = mid + 1;
            } else if(target < prefixSum[mid]) {
                hi = mid - 1;
                idx = mid;
            }
        }
        return idx;
    }
}
