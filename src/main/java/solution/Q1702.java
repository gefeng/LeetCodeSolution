package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Binary String After Change",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/maximum-binary-string-after-change/"
)
public class Q1702 {
    public String maximumBinaryString(String binary) {
        int n = binary.length();
        StringBuilder sb = new StringBuilder();

        int cntZero = 0;
        for(int i = 0; i < n; i++) {
            if(binary.charAt(i) == '0') {
                cntZero++;
            }
        }

        if(cntZero < 2) {
            return binary;
        }

        int idx = 0;
        while(idx < n && binary.charAt(idx) == '1') {
            sb.append('1');
            idx++;
        }

        while(cntZero != 1) {
            sb.append('1');
            cntZero--;
        }
        sb.append('0');

        while(sb.length() != n) {
            sb.append('1');
        }

        return sb.toString();
    }
}
