package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Sum of Unique Elements",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/sum-of-unique-elements/"
)
public class Q1748 {
    public int sumOfUnique(int[] nums) {
        int sum = 0;
        int[] freq = new int[101];
        for(int num : nums) {
            freq[num]++;
        }

        for(int i = 0; i < 101; i++) {
            if(freq[i] == 1) {
                sum += i;
            }
        }

        return sum;
    }
}
