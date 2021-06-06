package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Number of Flips to Make the Binary String Alternating",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/minimum-number-of-flips-to-make-the-binary-string-alternating/"
)
public class Q1888 {
    /*
    * 这题contest时候没做出来
    * 拼接原string后可以用sliding window来做
    * */
    public int minFlips(String s) {
        int n = s.length();
        String t1 = createTemplate(s, '0');
        String t2 = createTemplate(s, '1');

        s += s;

        int cnt1 = 0;
        int cnt2 = 0;
        int minCnt = Integer.MAX_VALUE;
        int l = 0;
        int r = 0;
        while(r < 2 * n) {
            cnt1 += (s.charAt(r) ^ t1.charAt(r));
            cnt2 += (s.charAt(r) ^ t2.charAt(r));
            if(r - l + 1 > n) {
                cnt1 -= (s.charAt(l) ^ t1.charAt(l));
                cnt2 -= (s.charAt(l) ^ t2.charAt(l));
                l++;
            }
            if(r - l + 1 == n) {
                minCnt = Math.min(minCnt, Math.min(cnt1, cnt2));
            }
            r++;
        }

        return minCnt;
    }

    private String createTemplate(String s, char init) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < 2 * n; i++) {
            sb.append(init);
            init = init == '0' ? '1' : '0';
        }

        return sb.toString();
    }
}
