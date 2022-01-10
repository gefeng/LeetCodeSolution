package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Self Dividing Numbers",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/self-dividing-numbers/"
)
public class Q728 {
    /**
     * Time:  O(N * logK)
     * Space: O(1)
     * */
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> ans = new ArrayList<>();

        for(int i = left; i <= right; i++) {
            if(isOk(i)) ans.add(i);
        }

        return ans;
    }

    private boolean isOk(int x) {
        int cur = x;
        while(cur != 0) {
            int d = cur % 10;
            if(d == 0 || x % d != 0) return false;
            cur /= 10;
        }
        return true;
    }
}
