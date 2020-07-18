package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;

@Problem(
        title = "Single Number",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/single-number/"
)
public class Q136 {
    public int singleNumber(int[] nums) {
        int sum = 0;
        for(int n : nums) {
            sum ^= n;
        }
        return sum;
    }
    public int singleNumberHash(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int sum = 0;
        for(int n : nums) {
            if(set.contains(n))
                sum -= n;
            else {
                sum += n;
                set.add(n);
            }
        }
        return sum;
    }
}
