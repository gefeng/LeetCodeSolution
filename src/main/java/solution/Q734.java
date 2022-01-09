package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Sentences Similarity",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/sentence-similarity/"
)
public class Q734 {
    /**
     * Time:  O(N + N)
     * Space: O(M)
     * */
    public boolean areSentencesSimilar(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        if(sentence1.length != sentence2.length) return false;

        Map<String, Set<String>> map = new HashMap<>();
        for(List<String> pair : similarPairs) {
            map.computeIfAbsent(pair.get(0), k -> new HashSet<>()).add(pair.get(1));
            map.computeIfAbsent(pair.get(1), k -> new HashSet<>()).add(pair.get(0));
        }

        int n = sentence1.length;

        for(int i = 0; i < n; i++) {
            String w1 = sentence1[i];
            String w2 = sentence2[i];
            if(w1.equals(w2)) continue;

            if(map.containsKey(w1) && map.get(w1).contains(w2)) {
                continue;
            }

            if(map.containsKey(w2) && map.get(w2).contains(w1)) {
                continue;
            }

            return false;
        }

        return true;
    }
}
