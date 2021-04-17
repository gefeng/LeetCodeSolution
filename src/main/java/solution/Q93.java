package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Restore IP Addresses",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/restore-ip-addresses/"
)
public class Q93 {
    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        backTrack(s, 0, 1, new ArrayList<>(), ans);
        return ans;
    }

    private void backTrack(String s, int start, int curr, List<String> candidate, List<String> ans) {
        if(start >= s.length())
            return;

        if(curr == 4) {
            String segment = s.substring(start, s.length());
            if(isValid(segment)) {
                StringBuilder sb = new StringBuilder();
                for(String ss : candidate)
                    sb.append(ss).append(".");
                sb.append(segment);
                ans.add(sb.toString());
            }
        } else {
            for(int end = start + 1; end <= s.length(); end++) {
                String segment = s.substring(start, end);
                if(isValid(segment)) {
                    candidate.add(segment);
                    backTrack(s, end, curr + 1, candidate, ans);
                    candidate.remove(candidate.size() - 1);
                }
            }
        }
    }

    private boolean isValid(String s) {
        int len = s.length();
        if(len == 0 || len > 3)
            return false;
        if(len > 1 && s.charAt(0) == '0')
            return false;

        int n = Integer.valueOf(s);
        return n >= 0 && n <= 255;
    }
}
