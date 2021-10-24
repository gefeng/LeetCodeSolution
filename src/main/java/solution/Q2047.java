package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of Valid Words in a Sentence",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/number-of-valid-words-in-a-sentence/"
)
public class Q2047 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int countValidWords(String sentence) {
        String[] tokens = sentence.trim().split("\\s+");

        int ans = 0;
        for(String t : tokens) {
            int cntH = 0;
            int cntP = 0;
            int idxH = 0;
            int idxP = 0;
            boolean hasDigit = false;
            for(int i = 0; i < t.length(); i++) {
                char c = t.charAt(i);
                if(Character.isLetter(c)) {
                    continue;
                }
                if(Character.isDigit(c)) {
                    hasDigit = true;
                } else if(c == '-') {
                    cntH++;
                    idxH = i;
                } else {
                    cntP++;
                    idxP = i;
                }
            }

            boolean ok = true;
            if(cntH > 1 || cntP > 1) {
                ok = false;
            }
            if(cntH == 1) {
                if(idxH == 0 || idxH == t.length() - 1) {
                    ok = false;
                } else {
                    if(!Character.isLetter(t.charAt(idxH - 1)) || !Character.isLetter(t.charAt(idxH + 1))) {
                        ok = false;
                    }
                }
            }
            if(cntP == 1) {
                if(idxP != t.length() - 1) {
                    ok = false;
                }
            }

            if(hasDigit) {
                ok = false;
            }

            if(ok) {
                ans++;
            }
        }
        return ans;
    }
}
