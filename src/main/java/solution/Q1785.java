package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Elements to Add to Form a Given Sum",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/minimum-elements-to-add-to-form-a-given-sum/"
)
public class Q1785 {
    public int minElements(int[] nums, int limit, int goal) {
        long sum = 0;
        for(int num : nums) {
            sum += num;
        }

        long remain = Math.abs(goal - sum);
        return remain % limit == 0 ? (int)(remain / limit) : (int)(remain / limit + 1);
    }
}
