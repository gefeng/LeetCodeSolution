package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "String Matching in an Array",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/string-matching-in-an-array/"
)
public class Q1408 {
    /**
     * Time:  O(N ^ 3)
     * Space: O(N)
     * */
    public List<String> stringMatching(String[] words) {
        int n = words.length;
        List<String> ans = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            String w = words[i];

            for(int j = 0; j < n; j++) {
                if(i != j) {
                    if(words[j].indexOf(w) >= 0) {
                        ans.add(w);
                        break;
                    }
                }
            }
        }

        return ans;
    }
}
