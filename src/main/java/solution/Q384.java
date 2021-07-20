package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.Random;

@Problem(
        title = "Shuffle an Array",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.NONE,
        url = "https://leetcode.com/problems/shuffle-an-array/"
)
public class Q384 {
    private static Random rand = new Random();
    private int[] origin;
    public Q384(int[] nums) {
        origin = Arrays.copyOfRange(nums, 0, nums.length);
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return origin;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int[] copy = Arrays.copyOfRange(origin, 0, origin.length);
        int n = copy.length;

        // 0 n - 3  i =2  2 - n - 1
        for(int i = 0; i < n; i++) {
            int j = rand.nextInt(n - i) + i;
            int temp = copy[i];
            copy[i] = copy[j];
            copy[j] = temp;
        }

        return copy;
    }
}
