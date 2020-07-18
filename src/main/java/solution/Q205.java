package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.HashMap;

@Problem(
        title = "Isomorphic Strings",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/isomorphic-strings/"
)
public class Q205 {
    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Integer> map1 = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();
        for(Integer i = 0; i < s.length(); i++) {
            if(map1.put(s.charAt(i), i) != map2.put(t.charAt(i), i))
                return false;
        }
        return true;
    }

    public boolean isIsomorphicAscii(String s, String t) {
        int[] map = new int[256];
        Arrays.fill(map, -1);
        for(int i = 0; i < s.length(); i++) {
            if(map[s.charAt(i)] != map[t.charAt(i) + 128])
                return false;
            map[s.charAt(i)] = i;
            map[t.charAt(i) + 128] = i;
        }
        return true;
    }
}
