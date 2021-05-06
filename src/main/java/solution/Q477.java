package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Total Hamming Distance",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BIT_MANIPULATION,
        url = "https://leetcode.com/problems/total-hamming-distance/"
)
public class Q477 {
    /*
    * Instead of comparing pair to pair, we can count bits.
    * */
    public int totalHammingDistance(int[] nums) {
        int n = nums.length;
        int hDist = 0;
        int[] count = new int[32];

        for(int num : nums) {
            int idx = 0;
            while(num != 0) {
                count[idx++] += num % 2;
                num >>= 1;
            }
        }

        for(int c : count) {
            hDist += c * (n - c);
        }

        return hDist;
    }
}
