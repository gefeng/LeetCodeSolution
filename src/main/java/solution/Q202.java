package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;

@Problem(
        title = "Happy Number",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/happy-number/"
)
public class Q202 {
    public boolean isHappy(int n) {
        int slow = n;
        int fast = n;
        do {
            slow = sumSquare(slow);
            fast = sumSquare(sumSquare(fast));
        } while(slow != fast);

        return slow == 1;
    }
    public boolean isHappyHash(int n) {
        HashSet<Integer> set = new HashSet<>();
        int sum = n;
        while(sum != 1 && !set.contains(sum)) {
            set.add(sum);
            sum = sumSquare(sum);
        }
        return sum == 1;
    }

    private int sumSquare(int n) {
        int sum = 0;
        while(n != 0) {
            sum += (n % 10) * (n % 10);
            n /= 10;
        }
        return sum;
    }
}
