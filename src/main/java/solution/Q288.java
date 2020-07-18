package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.HashSet;

@Problem(
        title = "Unique Word Abbreviation",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/unique-word-abbreviation/"
)
public class Q288 {
    HashMap<String, HashSet<String>> map;
    public Q288(String[] dictionary) {
        map = new HashMap<>();
        for(String s : dictionary) {
            String key = abbreviate(s);
            if(!map.containsKey(key))
                map.put(key, new HashSet<>());
            map.get(key).add(s);
        }
    }

    public boolean isUnique(String word) {
        String key = abbreviate(word);
        HashSet<String> set = map.get(key);
        if(set == null || (set.size() == 1 && set.contains(word)))
            return true;
        return false;
    }

    private String abbreviate(String s) {
        if(s.length() < 3)
            return s;
        StringBuilder sb = new StringBuilder();
        sb.append(s.charAt(0)).append(s.length() - 2).append(s.charAt(s.length() - 1));
        return sb.toString();
    }
}
