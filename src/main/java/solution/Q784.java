package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Letter Case Permutation",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/letter-case-permutation/"
)
public class Q784 {
    /**
     * Time:  O(2 ^ N * N)
     * Space: O(2 ^ N * N)
     * */
    public List<String> letterCasePermutation(String s) {
        char[] arr = s.toCharArray();
        List<String> ans = new ArrayList<>();

        dfs(arr, 0, ans);

        return ans;
    }

    private void dfs(char[] arr, int cur, List<String> ans) {
        if(cur == arr.length) {
            ans.add(new String(arr));
            return;
        }

        char c = arr[cur];
        dfs(arr, cur + 1, ans);

        if(Character.isLetter(c)) {
            char cc = c;
            if(c >= 'a' && c <= 'z') {
                cc = Character.toUpperCase(c);
            } else {
                cc = Character.toLowerCase(c);
            }

            arr[cur] = cc;
            dfs(arr, cur + 1, ans);
            arr[cur] = c;
        }
    }
}
