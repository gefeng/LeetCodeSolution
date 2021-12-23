package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Problem(
        title = "Most Common Word",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/most-common-word/"
)
public class Q819 {
    /**
     * Time:  O(M + N)
     * Space: O(M + N)
     * */
    public String mostCommonWord(String paragraph, String[] banned) {
        paragraph = paragraph.toLowerCase().replaceAll("[!?',;.]", " ");
        String[] arr = paragraph.split("\\s+");
        Set<String> ban = new HashSet<>();

        for(String s : banned) ban.add(s);

        Map<String, Integer> fMap = new HashMap<>();
        for(String s : arr) {
            if(!Character.isLetter(s.charAt(s.length() - 1))) {
                s = s.substring(0, s.length() - 1);
            }
            fMap.put(s, fMap.getOrDefault(s, 0) + 1);
        }

        String ans = "";
        int max = 0;
        for(String k : fMap.keySet()) {
            int f = fMap.get(k);
            if(!ban.contains(k) && f > max) {
                max = f;
                ans = k;
            }
        }

        return ans;
    }
}
