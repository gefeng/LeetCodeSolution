package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Iterator for Combination",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/iterator-for-combination/"
)
public class Q1286 {
    /**
     * Time:  O(2 ^ N * N)
     * Space: O(2 ^ N)
     * */
    String s;
    int len;
    List<String> combinations;
    int index = 0;
    public Q1286(String characters, int combinationLength) {
        s = characters;
        len = combinationLength;
        combinations = new ArrayList<>();
        findCombinations(0, new StringBuilder());
    }

    public String next() {
        return combinations.get(index++);
    }

    public boolean hasNext() {
        return index < combinations.size();
    }

    private void findCombinations(int start, StringBuilder comb) {
        for(int i = start; i < s.length(); i++) {
            comb.append(s.charAt(i));
            if(comb.length() == len)
                combinations.add(comb.toString());
            else
                findCombinations(i + 1, comb);
            comb.deleteCharAt(comb.length() - 1);
        }
    }
}
