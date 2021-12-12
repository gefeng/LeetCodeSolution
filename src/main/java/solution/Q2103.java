package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Rings and Rods",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/rings-and-rods/"
)
public class Q2103 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int countPoints(String rings) {
        int[][] cnt = new int[10][3];
        String rgb = "RGB";
        int n = rings.length();

        for(int i = 0; i < n; i += 2) {
            cnt[rings.charAt(i + 1) - '0'][rgb.indexOf(rings.charAt(i))] += 1;
        }

        int ans = 0;
        for(int i = 0; i < 10; i++) {
            if(cnt[i][0] > 0 && cnt[i][1] > 0 && cnt[i][2] > 0) {
                ans++;
            }
        }

        return ans;
    }
}
