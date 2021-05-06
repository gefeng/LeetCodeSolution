package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Add Bold Tag in String",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/add-bold-tag-in-string/"
)
public class Q616 {
    /*
    * n = len(s)
    * m = len(dict)
    * k = maxLen(word in dict)
    * O(n * m * k)
    * */
    public String addBoldTag(String s, String[] dict) {
        List<int[]> intervals = new ArrayList<>();

        for(int i = 0; i < s.length(); i++) {
            for(String word : dict) {
                if(s.startsWith(word, i)) {
                    int j = i + word.length();
                    if(intervals.isEmpty()) {
                        intervals.add(new int[] {i, j});
                    } else {
                        int[] last = intervals.get(intervals.size() - 1);
                        if(i <= last[1]) {
                            last[1] = Math.max(last[1], j);
                        } else {
                            intervals.add(new int[] {i, j});
                        }
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int i = 0;
        for(int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];
            while(i < start) {
                sb.append(s.charAt(i++));
            }
            sb.append("<b>");
            while(i < end) {
                sb.append(s.charAt(i++));
            }
            sb.append("</b>");
        }

        while(i < s.length()) {
            sb.append(s.charAt(i++));
        }

        return sb.toString();
    }
}
