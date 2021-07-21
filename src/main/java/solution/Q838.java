package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Push Dominoes",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/push-dominoes/"
)
public class Q838 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public String pushDominoes(String dominoes) {
        int n = dominoes.length();
        char[] d = dominoes.toCharArray();
        char[] res = new char[n];
        int[] l = new int[n];
        Arrays.fill(l, -1);

        int prev = -1;
        for(int i = 0; i < n; i++) {
            if(d[i] == '.') {
                l[i] = prev;
            } else {
                prev = i;
            }
        }

        prev = -1;
        for(int i = n - 1; i >= 0; i--) {
            if(d[i] == '.') {
                if(l[i] == -1 && prev == -1) {
                    res[i] = '.';
                } else if(l[i] == -1) {
                    res[i] = d[prev] == 'L' ? 'L' : '.';
                } else if(prev == -1) {
                    res[i] = d[l[i]] == 'R' ? 'R' : '.';
                } else if(i - l[i] == prev - i) {
                    res[i] = d[l[i]] == d[prev] ? d[l[i]] : '.';
                } else {
                    if(d[l[i]] == d[prev]) {
                        res[i] = d[l[i]];
                    } else if((d[l[i]] == 'L' && d[prev] == 'R')) {
                        res[i] = '.';
                    } else {
                        res[i] = i - l[i] > prev - i ? d[prev] : d[l[i]];
                    }
                }
            } else {
                prev = i;
                res[i] = d[i];
            }
        }

        return new String(res);
    }
}
