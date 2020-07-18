package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Problem(
        title = "Group Shifted Strings",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/group-shifted-strings/"
)
public class Q249 {
    public List<List<String>> groupStrings(String[] strings) {
        HashMap<String, List<String>> map = new HashMap<>();
        for(String s : strings) {
            StringBuilder sb = new StringBuilder();
            String key;
            for(int i = 0; i < s.length(); i++) {
                int dist = (s.charAt(i) - s.charAt(0) + 26) % 26;
                sb.append(dist).append(',');
            }
            key = sb.toString();

            if(!map.containsKey(key))
                map.put(key, new ArrayList<>());
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
    }
}
