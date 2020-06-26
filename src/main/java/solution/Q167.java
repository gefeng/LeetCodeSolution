package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Two Sum II - Input array is sorted",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/"
)
public class Q167 {
    public int[] twoSum(int[] numbers, int target) {
        int head = 0;
        int tail = numbers.length - 1;
        int sum = 0;
        while(true) {
            sum = numbers[head] + numbers[tail];
            if(sum == target)
                return new int[] {head + 1, tail + 1};
            else if(sum < target)
                head++;
            else
                tail--;
        }
    }
}
