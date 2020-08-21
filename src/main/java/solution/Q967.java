package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Numbers With Same Consecutive Differences",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DEPTH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/numbers-with-same-consecutive-differences/"
)
public class Q967 {
    List<Integer> nums = new ArrayList<>();
    public int[] numsSameConsecDiff(int N, int K) {
        for(int i = 0; i < 10; i++) {
            findNumbers(N - 1, K, i);
        }

        int[] ans = new int[nums.size()];
        for(int i = 0; i < nums.size(); i++)
            ans[i] = nums.get(i);
        return ans;
    }

    private void findNumbers(int N, int K, int num) {
        if(N == 0) {
            nums.add(num);
            return;
        }
        if(num == 0)
            return;

        int prevDigit = num % 10; // retrieve the last digit
        int digit1 = prevDigit + K;
        int digit2 = prevDigit - K;
        if(digit1 < 10)
            findNumbers(N - 1, K, num * 10 + digit1);
        if(digit2 != digit1 && digit2 >= 0)
            findNumbers(N - 1, K, num * 10 + digit2);
    }
}
