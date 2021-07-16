package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Palindrome Permutation II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/palindrome-permutation-ii/"
)
public class Q267 {
    public List<String> generatePalindromes(String s) {
        List<String> ans = new ArrayList<>();
        int n = s.length();
        int[] cnt = new int[26];

        for(int i = 0; i < n; i++) {
            cnt[s.charAt(i) - 'a']++;
        }

        dfs(cnt, 0, new char[n], ans);

        return ans;
    }

    private void dfs(int[] cnt, int idx, char[] cand, List<String> ans) {
        int n = cand.length;
        if(idx == (n + 1) / 2) {
            ans.add(new String(cand));
            return;
        }

        for(int i = 0; i < 26; i++) {
            if(cnt[i] == 0) {
                continue;
            }

            if(cnt[i] == 1 && idx != n - 1 - idx) {
                continue;
            }

            char c = (char)(i + 'a');
            cand[idx] = c;
            cand[n - 1 - idx] = c;
            cnt[i] = (idx == n - 1 - idx) ? cnt[i] - 1 : cnt[i] - 2;
            dfs(cnt, idx + 1, cand, ans);
            cnt[i] = (idx == n - 1 - idx) ? cnt[i] + 1 : cnt[i] + 2;
        }
    }
}
