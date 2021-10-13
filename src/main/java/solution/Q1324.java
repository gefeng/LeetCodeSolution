package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Print Words Vertically",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/print-words-vertically/"
)
public class Q1324 {
    /**
     * Time:  O(N * L)
     * Space: O(N * L)
     * */
    public List<String> printVertically(String s) {
        List<String> ans = new ArrayList<>();

        String[] arr = s.split(" ");
        for(int i = 0; i < 201; i++) {
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < arr.length; j++) {
                if(i < arr[j].length()) {
                    sb.append(arr[j].charAt(i));
                } else {
                    sb.append(" ");
                }
            }

            String v = sb.toString().stripTrailing();
            if(v.isEmpty()) {
                break;
            } else {
                ans.add(v);
            }
        }

        return ans;
    }
}
