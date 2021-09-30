package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Find the Minimum Number of Fibonacci Numbers Whose Sum Is K",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/find-the-minimum-number-of-fibonacci-numbers-whose-sum-is-k/"
)
public class Q1414 {
    /**
     * Time:  O(countFib(k))
     * Space: O(countFib(k))
     * */
    public int findMinFibonacciNumbers(int k) {
        int ans = 0;
        List<Integer> fib = new ArrayList<>();
        fib.add(1);
        fib.add(1);

        int curr = 1;
        int idx = 2;
        while(curr <= k) {
            curr = fib.get(idx - 2) + fib.get(idx - 1);
            fib.add(curr);
            idx++;
        }

        for(int i = fib.size() - 1; i >= 0; i--) {
            int f = fib.get(i);
            while(k - f >= 0) {
                k -= fib.get(i);
                ans++;
            }

            if(k == 0) {
                break;
            }
        }

        return ans;
    }
}
