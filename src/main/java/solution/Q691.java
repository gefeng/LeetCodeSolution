package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Stickers to Spell Word",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/stickers-to-spell-word/"
)
public class Q691 {
    /**
     * Time:  O(2 ^ N * M * N)
     * Space: O(2 ^ N + M)
     * */
    Map<String, int[]> map = new HashMap<>();
    public int minStickers(String[] stickers, String target) {
        int n = target.length();

        for(String s : stickers) {
            int[] cnt = new int[26];
            for(int i = 0; i < s.length(); i++) {
                cnt[s.charAt(i) - 'a']++;
            }
            map.put(s, cnt);
        }

        int ans = dfs(stickers, target, 0, new Integer[1 << n]);

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private int dfs(String[] stickers, String target, int state, Integer[] memo) {
        int n = target.length();

        if(state == (1 << n) - 1) return 0;
        if(memo[state] != null) return memo[state];

        int min = Integer.MAX_VALUE;

        for(String s : stickers) {
            int nstate = state;
            int[] cnt = Arrays.copyOf(map.get(s), 26);

            for(int i = 0; i < n; i++) {
                if((nstate & (1 << i)) == 0) {
                    char c = target.charAt(i);

                    if(cnt[c - 'a'] > 0) {
                        cnt[c - 'a']--;
                        nstate |= 1 << i;
                    }
                }
            }

            if(nstate != state) {
                int ret = dfs(stickers, target, nstate, memo);
                if(ret != Integer.MAX_VALUE) {
                    min = Math.min(min, ret + 1);
                }
            }
        }

        return memo[state] = min;
    }
}
