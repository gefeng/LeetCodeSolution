package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Swap For Longest Repeated Character Substring",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SLIDING_WINDOW,
        url = "https://leetcode.com/problems/swap-for-longest-repeated-character-substring/"
)
public class Q1156 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int maxRepOpt1(String text) {
        int n = text.length();
        int ans = 0;

        for(char c = 'a'; c <= 'z'; c++) {
            List<int[]> seq = new ArrayList<>();

            int l = 0, r = 0;
            while(r < n) {
                if(text.charAt(r) == c) {
                    l = r;
                    while(r < n && text.charAt(r) == c) {
                        r++;
                    }

                    seq.add(new int[] {l, r - 1});
                } else {
                    r++;
                }

            }

            int[] pre = null;
            for(int[] s : seq) {
                if(pre != null && s[0] - pre[1] == 2) {
                    if(seq.size() > 2) {
                        ans = Math.max(ans, s[1] - pre[0] + 1);
                    } else {
                        ans = Math.max(ans, s[1] - pre[0]);
                    }
                } else {
                    if(seq.size() > 1) {
                        ans = Math.max(ans, s[1] - s[0] + 2);
                    } else {
                        ans = Math.max(ans, s[1] - s[0] + 1);
                    }
                }

                pre = s;
            }
        }

        return ans;
    }
}
