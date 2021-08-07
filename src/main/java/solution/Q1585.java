package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Check If String Is Transformable With Substring Sort Operations",
        difficulty = QDifficulty.HARD,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/check-if-string-is-transformable-with-substring-sort-operations/"
)
public class Q1585 {
    /**
     * Any character can move(swapping with adjacent) to the left until hit a smaller one.
     * For any character in target string, we need to check whether or not we can perform
     * such movement for the same character in s. 
     *
     * Time:  O(N)
     * Space: O(N)
     * */
    public boolean isTransformable(String s, String t) {
        int n = s.length();
        List<Integer>[] pos = new List[10];
        int[] idx = new int[10];

        for(int i = 0; i < 10; i++) {
            pos[i] = new ArrayList<>();
        }

        for(int i = 0; i < n; i++) {
            pos[s.charAt(i) - '0'].add(i);
        }

        for(int i = 0; i < n; i++) {
            int c = t.charAt(i) - '0';

            if(idx[c] >= pos[c].size()) {
                return false;
            }

            for(int j = 0; j < c; j++) {
                if(idx[j] < pos[j].size() && pos[j].get(idx[j]) < pos[c].get(idx[c])) {
                    return false;
                }
            }

            idx[c]++;
        }

        return true;
    }
}
