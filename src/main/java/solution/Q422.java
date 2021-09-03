package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.List;

@Problem(
        title = "Valid Word Square",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATRIX,
        url = "https://leetcode.com/problems/valid-word-square/"
)
public class Q422 {
    /**
     * Time:  O(M * N)
     * Space: O(1)
     * */
    public boolean validWordSquare(List<String> words) {
        int n = words.size();

        for(int i = 0; i < n; i++) {
            String s = words.get(i);

            for(int j = 0; j < s.length(); j++) {

                if(j > n - 1 || i > words.get(j).length() - 1 || s.charAt(j) != words.get(j).charAt(i)) {
                    return false;
                }
            }
        }

        return true;
    }
}
