package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Shortest Word Distance III",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/shortest-word-distance-iii/"
)
public class Q245 {
    public int shortestWordDistance(String[] wordsDict, String word1, String word2) {
        if(word1.equals(word2)) {
            return shortest(wordsDict, word1);
        } else {
            return shortest(wordsDict, word1, word2);
        }
    }

    private int shortest(String[] dict, String s) {
        int min = Integer.MAX_VALUE;
        int prev = -1;
        for(int i = 0; i < dict.length; i++) {
            if(dict[i].equals(s)) {
                if(prev != -1) {
                    min = Math.min(min, i - prev);
                }
                prev = i;
            }
        }
        return min;
    }

    private int shortest(String[] dict, String s1, String s2) {
        int n = dict.length;
        int min = Integer.MAX_VALUE;
        int last1 = -1;
        int last2 = -1;

        for(int i = 0; i < n; i++) {
            String s = dict[i];
            if(s.equals(s1)) {
                last1 = i;
            } else if(s.equals(s2)) {
                last2 = i;
            }

            if(last1 != -1 && last2 != -1) {
                min = Math.min(min, Math.abs(last1 - last2));
            }
        }

        return min;
    }
}
