package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Problem(
        title = "Strobogrammatic Number II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.RECURSION,
        url = "https://leetcode.com/problems/strobogrammatic-number-ii/"
)
public class Q247 {
    /*
    * Similar to build a palindrome number
    * */
    private static final String[] stroDigits = new String[] {"0", "1", "8", "6", "9"};
    public List<String> findStrobogrammatic(int n) {
        if(n == 1) {
            return Arrays.asList("0", "1", "8");
        }

        List<String> ans = new ArrayList<>();
        backTrack(n, 0, new StringBuilder(), ans);
        return ans;
    }

    private void backTrack(int n, int curr, StringBuilder candidate, List<String> ans) {
        if(curr == n / 2) {
            buildStrobogrammatic(n, candidate.toString(), ans);
            return;
        }

        for(int i = 0; i < stroDigits.length; i++) {
            String digit = stroDigits[i];
            if(curr == 0 && stroDigits[i].equals("0"))
                continue;
            candidate.append(digit);
            backTrack(n, curr + 1, candidate, ans);
            candidate.deleteCharAt(candidate.length() - 1);
        }
    }

    private void buildStrobogrammatic(int n, String firstHalf, List<String> ans) {
        StringBuilder fstHalf = new StringBuilder(firstHalf);
        StringBuilder secHalf = new StringBuilder(firstHalf).reverse();

        for(int i = 0; i < secHalf.length(); i++) {
            char c = secHalf.charAt(i);
            if(c == '6') {
                secHalf.setCharAt(i, '9');
            } else if(c == '9') {
                secHalf.setCharAt(i, '6');
            }
        }

        if(n % 2 != 0) {
            for(int i = 0; i < 3; i++) {
                StringBuilder sb = new StringBuilder();
                sb.append(fstHalf).append(stroDigits[i]).append(secHalf);
                ans.add(sb.toString());
            }
        } else {
            fstHalf.append(secHalf);
            ans.add(fstHalf.toString());
        }
    }
}
