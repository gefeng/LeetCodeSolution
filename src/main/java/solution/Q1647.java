package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Minimum Deletions to Make Character Frequencies Unique",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/"
)
public class Q1647 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int minDeletions(String s) {
        int ans = 0;
        int n = s.length();
        int[] cnt = new int[26];

        for(int i = 0; i < n; i++) {
            cnt[s.charAt(i) - 'a']++;
        }

        Set<Integer> seen = new HashSet<>();
        for(int i = 0; i < 26; i++) {
            if(cnt[i] == 0) {
                continue;
            }

            while(cnt[i] > 0 && seen.contains(cnt[i])) {
                cnt[i]--;
                ans++;
            }

            if(cnt[i] != 0) {
                seen.add(cnt[i]);
            }
        }

        return ans;
    }
}
