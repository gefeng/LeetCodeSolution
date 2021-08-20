package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Sort Characters By Frequency",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/sort-characters-by-frequency/"
)
public class Q451 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public String frequencySort(String s) {
        int n = s.length();
        int[] cnt = new int[62];

        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if(c >= 'a' && c <= 'z') {
                cnt[c - 'a']++;
            } else if(c >= 'A' && c <= 'Z') {
                cnt[c - 'A' + 26]++;
            } else {
                cnt[c - '0' + 52]++;
            }
        }

        StringBuilder sb = new StringBuilder();
        while(true) {
            int maxIdx = -1;
            int maxCnt = 0;
            for(int i = 0; i < 62; i++) {
                if(cnt[i] > maxCnt) {
                    maxCnt = cnt[i];
                    maxIdx = i;
                }
            }

            if(maxIdx == -1) {
                break;
            }

            char c = getChar(maxIdx);
            while(cnt[maxIdx]-- != 0) {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    private char getChar(int i) {
        char c;
        if(i >= 0 && i < 26) {
            c = (char)(i + 'a');
        } else if(i >= 26 && i < 52) {
            c = (char)(i - 26 + 'A');
        } else {
            c = (char)(i - 52 + '0');
        }
        return c;
    }
}
