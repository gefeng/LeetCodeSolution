package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "IP to CIDR",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BIT_MANIPULATION,
        url = "https://leetcode.com/problems/ip-to-cidr/"
)
public class Q751 {
    /**
     * Time:  O(logN)
     * Space: O(logN)
     * */
    public List<String> ipToCIDR(String ip, int n) {
        List<String> ans = new ArrayList<>();

        String[] s = ip.split("\\.");
        long cur = 0;
        for(String p : s) cur = (cur << 8) | Long.parseLong(p);

        while(n > 0) {
            long len = cur == 0 ? 1L << 32 : cur & (-cur);

            while(len > n) {
                len >>= 1;
            }
            String cidr = ip(cur) + "/" + (32 - Long.numberOfTrailingZeros(len));
            ans.add(cidr);
            n -= len;
            cur += len;
        }

        return ans;
    }

    private String ip(long x) {
        StringBuilder sb = new StringBuilder();

        for(int i = 3; i >= 0; i--) {
            sb.append(((255 << i * 8) & x) >> i * 8).append(".");
        }

        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }
}
