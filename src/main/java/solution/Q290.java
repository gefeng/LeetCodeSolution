package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.HashSet;

@Problem(
        title = "Word Pattern",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/word-pattern/"
)
public class Q290 {
    public boolean wordPattern(String pattern, String str) {
        HashMap<Character, String> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        String[] words = str.split("\\s+");
        if(words.length != pattern.length())
            return false;

        for(int i = 0; i < pattern.length(); i++) {
            char p = pattern.charAt(i);
            String word = words[i];
            if(!map.containsKey(p)) {
                if(set.contains(words[i]))
                    return false;
                else {
                    set.add(word);
                    map.put(p, word);
                }
            }
            else {
                if(!map.get(p).equals(word))
                    return false;
            }
        }

        return true;
    }
}
