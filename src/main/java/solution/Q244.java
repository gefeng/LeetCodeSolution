package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Problem(
        title = "Shortest Word Distance II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TWO_POINTERS,
        url = "https://leetcode.com/problems/shortest-word-distance-ii/"
)
public class Q244 {
    private Map<String, List<Integer>> idxMap;
    public Q244(String[] wordsDict) {
        int n = wordsDict.length;
        idxMap = new HashMap<>();
        for(int i = 0; i < n; i++) {
            List<Integer> indices = idxMap.computeIfAbsent(wordsDict[i], k -> new ArrayList<>());
            indices.add(i);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> l1 = idxMap.get(word1);
        List<Integer> l2 = idxMap.get(word2);
        int m = l1.size();
        int n = l2.size();
        int minDist = Integer.MAX_VALUE;

        int i = 0;
        int j = 0;
        while(i < m && j < n) {
            int idx1 = l1.get(i);
            int idx2 = l2.get(j);
            if(idx2 > idx1) {
                i++;
            } else {
                j++;
            }
            minDist = Math.min(minDist, Math.abs(idx1 - idx2));
        }

        return minDist;
    }
}
