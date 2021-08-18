package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Lexicographical Numbers",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DEPTH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/lexicographical-numbers/"
)
public class Q386 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();

        for(int i = 1; i <= 9; i++) {
            dfs(n, i, res);
        }

        return res;
    }

    private void dfs(int n, int num, List<Integer> res) {
        if(num > n) {
            return;
        }

        res.add(num);

        for(int i = 0; i <= 9; i++) {
            dfs(n, num * 10 + i, res);
        }
    }
}
