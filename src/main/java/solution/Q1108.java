package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Defanging an IP Address",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/defanging-an-ip-address/"
)
public class Q1108 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public String defangIPaddr(String address) {
        StringBuilder ipBuilder = new StringBuilder();
        for(int i = 0; i < address.length(); i++) {
            char ch = address.charAt(i);
            ipBuilder.append(ch == '.' ? "[.]" : ch);
        }
        return ipBuilder.toString();
    }
}
