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

        return canForm(a, b) || canForm(b, a);
    }

    private boolean isPalindrome(String s, int i, int j) {
        while(i < j) {
            if(s.charAt(i++) != s.charAt(j--)) {
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

        return isPalindrome(a, i, j) || isPalindrome(b, i, j);
    }
}
