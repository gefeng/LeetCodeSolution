package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Verbal Arithmetic Puzzle",
        difficulty = QDifficulty.HARD,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/verbal-arithmetic-puzzle/"
)
public class Q1307 {
    /**
     * Time:  O(N!)
     * Space: O(1)
     * */
    public boolean isSolvable(String[] words, String result) {
        Set<Character> set = new HashSet<>();
        boolean[] init = new boolean[26];

        for(String w : words) {
            int len = w.length();
            if(len > 1) {
                init[w.charAt(0) - 'A'] = true;
            }

            for(int i = 0; i < w.length(); i++) {
                set.add(w.charAt(i));
            }
        }

        int n = result.length();
        if(n > 1) {
            init[result.charAt(0) - 'A'] = true;
        }
        for(int i = 0; i < n; i++) {
            set.add(result.charAt(i));
        }

        if(set.size() > 10) {
            return false;
        }

        char[] letters = new char[set.size()];
        int idx = 0;
        for(char c : set) {
            letters[idx++] = c;
        }

        int[] map = new int[26];
        Arrays.fill(map, -1);

        return dfs(words, result, letters, 0, 0, init, map);
    }

    private boolean dfs(String[] words, String result, char[] letters, int cur, int mask, boolean[] init, int[] map) {
        if(cur == letters.length) {
            if(solve(words, result, map)) {
                // for(int i = 0; i < 26; i++) {
                //     //System.out.println((char)(i + 'A') + " " + map[i]);
                // }
                return true;
            }
            return false;
        }

        char c = letters[cur];

        for(int i = 0; i < 10; i++) {
            if(((1 << i) & mask) != 0) {
                continue;
            }

            if(init[c - 'A'] && i == 0) {
                continue;
            }

            map[c - 'A'] = i;
            if(dfs(words, result, letters, cur + 1, mask | (1 << i), init, map)) {
                return true;
            }
            map[c - 'A'] = -1;
        }

        return false;
    }

    private boolean solve(String[] words, String result, int[] map) {
        int l = 0, r = 0;
        for(String w : words) {
            int len = w.length();
            int num = 0;
            for(int i = 0; i < len; i++) {
                int d = map[w.charAt(i) - 'A'];
                num = num * 10 + d;
            }
            l += num;
        }

        for(int i = 0; i < result.length(); i++) {
            r = r * 10 + map[result.charAt(i) - 'A'];
        }

        return l == r;
    }
}
