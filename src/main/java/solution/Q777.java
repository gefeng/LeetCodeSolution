package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Swap Adjacent in LR String",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/swap-adjacent-in-lr-string/"
)
public class Q777 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public boolean canTransform(String start, String end) {
        int n = start.length();

        String s1 = removex(start);
        String s2 = removex(end);

        if(!s1.equals(s2)) return false;


        for(int i = 0, j = 0; i < n && j < n; i++, j++) {
            while(i < n && start.charAt(i) != 'L') i++;
            while(j < n && end.charAt(j) != 'L') j++;

            if(j > i) return false;
        }

        for(int i = 0, j = 0; i < n && j < n; i++, j++) {
            while(i < n && start.charAt(i) != 'R') i++;
            while(j < n && end.charAt(j) != 'R') j++;
            if(j < i) return false;
        }

        return true;
    }

    private String removex(String s) {
        int n = s.length();

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == 'X') continue;
            sb.append(s.charAt(i));
        }

        return sb.toString();
    }
}
