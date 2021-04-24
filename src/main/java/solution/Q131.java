package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Palindrome Partition",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/palindrome-partitioning/"
)
public class Q131 {
    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        partition(s, 0, new ArrayList<>(), ans);
        return ans;
    }

    private void partition(String s, int start, List<String> candidate, List<List<String>> ans) {
        if(start == s.length()) {
            ans.add(new ArrayList<>(candidate));
        }

        for(int end = start + 1; end <= s.length(); end++) {
            if(isPalindrome(s, start, end - 1)) {
                candidate.add(s.substring(start, end));
                partition(s, end, candidate, ans);
                candidate.remove(candidate.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s, int start, int end) {
        while(start < end) {
            if(s.charAt(start++) != s.charAt(end--)) {
                return false;
            }
        }

        return true;
    }
}
