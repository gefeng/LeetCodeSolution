package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Problem(
        title = "Remove Invalid Parentheses",
        difficulty = QDifficulty.HARD,
        tag = QTag.DEPTH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/remove-invalid-parentheses/"
)
public class Q301 {
    /*
    * 这个解法time O(n*2^n) 我觉得够了，可以进一步优化为2^n（可以一边判断是否valid一边构建candidates）但是不好理解
    * 这题dfs思路是对于每个brackets可以保留也可以删除，典型的二叉树展开
    * */
    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        int[] invalidBrackets = toRemove(s);
        int left = invalidBrackets[0];
        int right = invalidBrackets[1];

        if(left == 0 && right == 0) {
            ans.add(s);
        } else {
            HashSet<String> ansSet = new HashSet<>();
            findValidString(s, 0, new StringBuilder(), left, right, ansSet);
            ans.addAll(ansSet);
        }

        return ans;
    }

    private void findValidString(String s, int idx, StringBuilder sb, int left, int right, Set<String> ans) {
        if(idx == s.length()) {
            String candidate = sb.toString();
            int[] invalid = toRemove(candidate);
            if(invalid[0] == 0 && invalid[1] == 0)
                ans.add(candidate);
            return;
        }

        char c = s.charAt(idx);
        sb.append(c);
        findValidString(s, idx + 1, sb, left, right, ans);
        sb.deleteCharAt(sb.length() - 1);

        if(c == '(' && left != 0) {
            findValidString(s, idx + 1, sb, left - 1, right, ans);
        }
        if(c == ')' && right != 0) {
            findValidString(s, idx + 1, sb, left, right - 1, ans);
        }
    }

    private int[] toRemove(String s) {
        int left = 0;
        int right = 0;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '(')
                left++;
            if(c == ')') {
                if(left == 0)
                    right++;
                else
                    left--;
            }
        }
        return new int[] {left, right};
    }
}
