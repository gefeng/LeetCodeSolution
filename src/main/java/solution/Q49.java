package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Problem(
        title = "Group Anagrams",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/group-anagrams/"
)
public class Q49 {
    /**
     * L denotes maximum length of strs[i]
     * Time:   O(N * L)
     * Space:  O(N * L)
     * */
    public List<List<String>> groupAnagramsCount(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for(String s : strs) {
            int count[] = new int[26];
            for(int i = 0; i < s.length(); i++)
                count[s.charAt(i) - 'a']++;
            StringBuilder sb = new StringBuilder();
            for(int c : count)
                sb.append(c).append(',');
            String key = sb.toString();
            if(!map.containsKey(key))
                map.put(key, new ArrayList<>());
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
    }
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, Integer> map = new HashMap<>();
        List<List<String>> group = new ArrayList<>();
        int size = 0;

        for(String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String sorted = new String(ca);
            Integer index = map.get(sorted);
            if(index == null) {
                index = size;
                map.put(sorted, size++);
                group.add(new ArrayList<>());
            }
            group.get(index).add(s);
        }
        return group;
    }
}
