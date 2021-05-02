package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Random;

@Problem(
        title = "Random Pick Index",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.RESERVOIR_SAMPLING,
        url = "https://leetcode.com/problems/random-pick-index/"
)
public class Q398 {
    private static Random rand = new Random();
    int[] nums;
    public Q398(int[] nums) {
        this.nums = nums;
    }

    public int pick(int target) {
        int idx = 0;
        int count = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == target) {
                count++;

                if(rand.nextInt(count) == 0) {
                    idx = i;
                }
            }
        }

        return idx;
    }
}
