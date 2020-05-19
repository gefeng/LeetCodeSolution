package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Buddy Strings",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/buddy-strings/"
)
public class Q859 {
    public boolean buddyStrings(String A, String B) {
        if(A.length() != B.length() || A.length() == 1 || B.length() == 1)
            return false;
        if(A.equals(B)) {
            int[] count = new int[26];
            for(int i = 0; i < A.length(); i++) {
                if(++count[A.charAt(i) - 'a'] > 1)
                    return true;
            }
            return false;
        }

        char[] ca = A.toCharArray();
        char[] cb = B.toCharArray();
        for(int i = 0; i < ca.length; i++) {
            if(ca[i] != cb[i]) {
                for(int j = i + 1; j < A.length(); j++) {
                    if(ca[j] == cb[i]) {
                        char temp = ca[i];
                        ca[i] = ca[j];
                        ca[j] = temp;
                        break;
                    }
                }
                break;
            }
        }

        return new String(ca).equals(new String(cb));
    }
}
