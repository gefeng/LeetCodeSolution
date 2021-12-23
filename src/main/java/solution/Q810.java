package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Chalkboard XOR Game",
        difficulty = QDifficulty.HARD,
        tag = QTag.BIT_MANIPULATION,
        url = "https://leetcode.com/problems/chalkboard-xor-game/"
)
public class Q810 {
    /**
     * Alice always win if xor(nums) == 0 or len(nums) is even.
     *
     * second winning condition proved by contradiction:
     *  Assume
     *  1. len(s) % 2 == 0
     *  2. s = x1 ^ x2 ^ ... ^ xn != 0 but no moves for alice
     *  => s ^ x = 0 for any x from s.
     *  => s ^ x1 ^ s ^ x2 ^ ... s ^ xn = 0
     *  => x1 ^ x2 ^ ... ^ xn = 0 which is contradicted to assumption 2.
     *
     * Time:  O(N)
     * Space: O(1)
     * */
    public boolean xorGame(int[] nums) {
        int n = nums.length;

        int xor = 0;
        for(int x : nums) {
            xor ^= x;
        }

        return xor == 0 || n % 2 == 0;
    }
}
