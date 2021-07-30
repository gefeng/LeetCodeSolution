package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Split Two Strings to Make Palindrome",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TWO_POINTERS,
        url = "https://leetcode.com/problems/split-two-strings-to-make-palindrome/"
)
public class Q1616 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public boolean checkPalindromeFormation(String a, String b) {
        int n = a.length();

        if(isPalindrome(a) || isPalindrome(b)) {
            return true;
        }

        return canForm(a, b) || canForm(b, a);
    }

    private boolean isPalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;

        while(l < r) {
            if(s.charAt(l++) != s.charAt(r--)) {
                return false;
            }
        }

        return true;
    }

    private boolean canForm(String a, String b) {
        int n = a.length();
        int i = 0;
        int j = n - 1;

        while(i < j) {
            if(a.charAt(i) != b.charAt(j)) {
                break;
            }
            i++;
            j--;
        }

        if(i >= j) {
            return true;
        }

        int ii = i;
        int jj = j;
        while(ii < jj) {
            if(a.charAt(ii) != a.charAt(jj)) {
                break;
            }
            ii++;
            jj--;
        }

        if(ii >= jj) {
            return true;
        }

        ii = i;
        jj = j;
        while(ii < jj) {
            if(b.charAt(ii) != b.charAt(jj)) {
                return false;
            }
            ii++;
            jj--;
        }
        return true;
    }
}
