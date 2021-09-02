package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Flip Game",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/flip-game/"
)
public class Q293 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N ^ 2)
     * */
    public List<String> generatePossibleNextMoves(String currentState) {
        List<String> res = new ArrayList<>();
        int n = currentState.length();

        char[] cArr = currentState.toCharArray();

        for(int i = 1; i < n; i++) {
            if(cArr[i - 1] == '+' && cArr[i] == '+') {
                cArr[i - 1] = '-';
                cArr[i] = '-';
                res.add(new String(cArr));
                cArr[i - 1] = '+';
                cArr[i] = '+';
            }
        }

        return res;
    }
}
