package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Hexspeak",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/hexspeak/"
)
public class Q1271 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public String toHexspeak(String num) {
        long dec = Long.parseLong(num);

        List<Character> hex = new ArrayList<>();
        while(dec != 0) {
            long d = dec % 16;
            if(d >= 0 && d < 10) {
                hex.add((char)(d + '0'));
            } else {
                hex.add((char)(d - 10 + 'A'));
            }
            dec /= 16;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < hex.size(); i++) {
            if(hex.get(i) == '0') {
                sb.append('O');
            } else if(hex.get(i) == '1') {
                sb.append('I');
            } else if(Character.isDigit(hex.get(i))) {
                return "ERROR";
            } else {
                sb.append(hex.get(i));
            }
        }

        return sb.reverse().toString();
    }
}
