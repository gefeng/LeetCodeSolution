package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Find the Longest Substring Containing Vowels in Even Counts",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BIT_MANIPULATION,
        url = "https://leetcode.com/problems/find-the-longest-substring-containing-vowels-in-even-counts/"
)
public class Q1371 {
    /*
        1 0 0 1 0
        u o i e a

        00000
        10100
        10100
        2^5
        0: means char appears even times
        1: means char apperas odd  times
    */
    public int findTheLongestSubstring(String s) {
        int n = s.length();
        int max = 0;
        int[] indices = new int[32];
        Arrays.fill(indices, -1);

        int prevState = 0;
        int currState = 0;
        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                currState = prevState ^ (1 << getMapping(c));
            } else {
                currState = prevState;
            }

            if(currState == 0) {
                max = i + 1;
            } else {
                if(indices[currState] != -1) {
                    max = Math.max(max, i - indices[currState]);
                } else {
                    indices[currState] = i;
                }
            }

            prevState = currState;
        }

        return max;
    }

    private int getMapping(char c) {
        int i = 0;
        switch(c) {
            case 'a' :
                i = 0;
                break;
            case 'e' :
                i = 1;
                break;
            case 'i' :
                i = 2;
                break;
            case 'o' :
                i = 3;
                break;
            case 'u' :
                i = 4;
                break;
        }

        return i;
    }
}
