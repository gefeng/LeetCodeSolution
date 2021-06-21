package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.List;

@Problem(
        title = "Count Items Matching a Rule",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/count-items-matching-a-rule/"
)
public class Q1773 {
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int cnt = 0;
        int type = 0;
        if(ruleKey.equals("type")) {
            type = 0;
        } else if(ruleKey.equals("color")) {
            type = 1;
        } else {
            type = 2;
        }
        for(List<String> item : items) {
            if(item.get(type).equals(ruleValue)) {
                cnt++;
            }
        }
        return cnt;
    }
}
