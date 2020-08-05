package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Problem(
        title = "Letter Combinations of a Phone Number",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/letter-combinations-of-a-phone-number/"
)
public class Q17 {
    HashMap<Character, String> map = new HashMap<>();
    List<String> ans = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    public List<String> letterCombinations(String digits) {
        if(digits.length() == 0)
            return ans;

        buildMap();
        backTrack(digits, 0);
        return ans;
    }

    private void buildMap() {
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
    }

    private void backTrack(String digits, int n) {
        String letters = map.get(digits.charAt(n));
        for(int i = 0; i < letters.length(); i++) {
            sb.append(letters.charAt(i));
            if(sb.length() == digits.length())
                ans.add(sb.toString());
            else
                backTrack(digits, n + 1);
            sb.deleteCharAt(sb.length()  - 1);
        }
    }
}
