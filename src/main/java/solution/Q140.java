package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Word Break II",
        difficulty = QDifficulty.HARD,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/word-break-ii/"
)
public class Q140 {
    public List<String> wordBreak(String s, List<String> wordDict) {
        HashSet<String> dict = new HashSet<>();
        for(String word : wordDict)
            dict.add(word);

        return dfsWordBreak(s, 0, dict, new HashMap<>());
    }

    private List<String> dfsWordBreak(String s, int start, HashSet<String> dict, HashMap<Integer, List<String>> memo) {
        if(start == s.length()) {
            return Arrays.asList("");
        }

        if(memo.containsKey(start))
            return memo.get(start);

        List<String> suffix = new ArrayList<>();
        for(int end = start + 1; end <= s.length(); end++) {
            String word = s.substring(start, end);
            if(dict.contains(word)) {
                List<String> ret = dfsWordBreak(s, end, dict, memo);
                if(ret.size() != 0) {
                    for(String sub : ret) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(word);
                        if(!sub.isEmpty())
                            sb.append(" ").append(sub);
                        suffix.add(sb.toString());
                    }
                }
            }
        }

        memo.put(start, suffix);
        return suffix;
    }
}
