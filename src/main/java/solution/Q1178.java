package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Problem(
        title = "Number of Valid Words for Each Puzzle",
        difficulty = QDifficulty.HARD,
        tag = QTag.BIT_MANIPULATION,
        url = "https://leetcode.com/problems/number-of-valid-words-for-each-puzzle/"
)
public class Q1178 {
    /**
     * Time:  O(M * L + N * 2 ^ 7)
     * Space: O(M * L)
     * */
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        int m = words.length;
        int n = puzzles.length;
        List<Integer> ans = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();

        for(String w : words) {
            boolean[] seen = new boolean[26];
            int len = w.length();
            int cnt = 0;
            for(int i = 0; i < len; i++) {
                char c = w.charAt(i);
                cnt += !seen[c - 'a'] ? 1 : 0;
                seen[c - 'a'] = true;
            }
            if(cnt > 7) {
                continue;
            }

            int mask = 0;
            for(int i = 0; i < 26; i++) {
                if(seen[i]) {
                    mask |= (1 << i);
                }
            }

            map.put(mask, map.getOrDefault(mask, 0) + 1);
        }

        for(String p : puzzles) {
            int mask = 0;
            int tot = 0;
            for(int i = 0; i < p.length(); i++) {
                mask |= (1 << p.charAt(i) - 'a');
            }

            int init = p.charAt(0) - 'a';

            for(int sm = mask; sm > 0; sm = (sm - 1) & mask) {
                if((sm & (1 << init)) != 0) {
                    tot += map.getOrDefault(sm, 0);
                }
            }

            ans.add(tot);
        }
        return ans;
    }
}
