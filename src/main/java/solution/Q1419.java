package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Number of Frogs Croaking",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/minimum-number-of-frogs-croaking/"
)
public class Q1419 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int minNumberOfFrogs(String croakOfFrogs) {
        int n = croakOfFrogs.length();
        int ans = 0;
        String ck = "croak";
        int[] cnt = new int[5];
        int cntFrog = 0;

        for(int i = 0; i < n; i++) {
            char c = croakOfFrogs.charAt(i);

            cnt[ck.indexOf(c + "")]++;

            if(c == 'k') {
                for(int j = 0; j < 5; j++) {
                    cnt[j]--;
                    if(cnt[j] < 0) {
                        return -1;
                    }
                }
                cntFrog--;
                if(cntFrog < 0) {
                    return -1;
                }
            } else if(c == 'c') {
                for(int j = 1; j < 5; j++) {
                    if(cnt[j] > cnt[0]) {
                        return -1;
                    }
                }
                cntFrog++;
                ans = Math.max(ans, cntFrog);
            }
        }

        for(int i = 0; i < 5; i++) {
            if(cnt[i] != 0) {
                return -1;
            }
        }

        return ans;
    }
}
