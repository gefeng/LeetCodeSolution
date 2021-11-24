package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Problem(
        title = "Powerful Integers",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/powerful-integers/"
)
public class Q970 {
    /**
     * Time:  O(logN * logN)
     * Space: O(logN * logN)
     * */
    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        Set<Integer> ans = new HashSet<>();

        for(int i = 0; ;i++) {
            int p1 = (int)Math.pow(x, i);
            if(p1 > bound) {
                break;
            }
            for(int j = 0; ;j++) {
                int p2 = (int)Math.pow(y, j);
                if(p1 + p2 > bound) {
                    break;
                }

                ans.add(p1 + p2);
                if(y == 1) {
                    break;
                }
            }

            if(x == 1) {
                break;
            }
        }

        return new ArrayList<>(ans);
    }
}
