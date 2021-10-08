package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Largest Multiple of Three",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/largest-multiple-of-three/"
)
public class Q1363 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public String largestMultipleOfThree(int[] digits) {
        int n = digits.length;

        Arrays.sort(digits);

        int rem = 0;
        for(int i = 0; i < n; i++) {
            rem = (rem + digits[i] % 3) % 3;
        }

        StringBuilder sb = new StringBuilder();
        if(rem == 1) {
            int remove = -1;
            for(int i = 0; i < n; i++) {
                if(digits[i] % 3 == 1) {
                    remove = i;
                    break;
                }
            }
            if(remove == -1) {
                for(int i = n - 1; i >= 0; i--) {
                    if(digits[i] % 3 == 0) {
                        sb.append((char)(digits[i] + '0'));
                    }
                }
            } else {
                for(int i = n - 1; i >= 0; i--) {
                    if(i != remove) {
                        sb.append((char)(digits[i] + '0'));
                    }
                }
            }
        } else if(rem == 2) {
            int remove = -1;
            for(int i = 0; i < n; i++) {
                if(digits[i] % 3 == 2) {
                    remove = i;
                    break;
                }
            }

            if(remove == -1) {
                for(int i = n - 1; i >= 0; i--) {
                    if(digits[i] % 3 == 0) {
                        sb.append((char)(digits[i] + '0'));
                    }
                }
            } else {
                for(int i = n - 1; i >= 0; i--) {
                    if(i != remove) {
                        sb.append((char)(digits[i] + '0'));
                    }
                }
            }
        } else {
            for(int i = n - 1; i >= 0; i--) {
                sb.append((char)(digits[i] + '0'));
            }
        }

        return trim(sb.toString());
    }

    private String trim(String s) {
        int n = s.length();
        int i = 0;
        while(i < n - 1 && s.charAt(i) == '0') {
            i++;
        }
        return s.substring(i, n);
    }
}
