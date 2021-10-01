package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of Steps to Reduce a Number in Binary Representation to One",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BIT_MANIPULATION,
        url = "https://leetcode.com/problems/number-of-steps-to-reduce-a-number-in-binary-representation-to-one/"
)
public class Q1404 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    public int numSteps(String s) {
        int ans = 0;
        int n = s.length();
        char[] arr = s.toCharArray();

        while(!isOne(arr)) {
            if(arr[arr.length - 1] == '0') {
                arr = shift(arr);
            } else {
                arr = plusOne(arr);
            }
            ans++;
        }

        return ans;
    }

    private boolean isOne(char[] arr) {
        int n = arr.length;
        for(int i = 0; i < n - 1; i++) {
            if(arr[i] == '1') {
                return false;
            }
        }

        return arr[n - 1] == '1';
    }

    private char[] shift(char[] arr) {
        int n = arr.length;
        char[] ret = new char[n - 1];
        for(int i = 0; i < n - 1; i++) {
            ret[i] = arr[i];
        }
        return ret;
    }

    private char[] plusOne(char[] arr) {
        int n = arr.length;
        int carry = 0;

        int j = n - 1;
        for(int i = n - 1; i >= 0; i--) {
            if(arr[i] == '1') {
                j--;
            } else {
                break;
            }
        }

        char[] ret = null;
        if(j == -1) {
            ret = new char[n + 1];
            ret[0] = '1';
            for(int i = 1; i < n + 1; i++) {
                ret[i] = '0';
            }
        } else {
            ret = new char[n];
            for(int i = 0; i < j; i++) {
                ret[i] = arr[i];
            }
            ret[j] = '1';
            for(int i = j + 1; i < n; i++) {
                ret[i] = '0';
            }
        }

        return ret;
    }
}
