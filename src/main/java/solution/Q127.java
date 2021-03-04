package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Word Ladder",
        difficulty = QDifficulty.HARD,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/word-ladder/"
)
public class Q127 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> dict = new HashSet<>();
        for(String word : wordList)
            dict.add(word);

        int minLen = bfs(dict, beginWord, endWord);
        return minLen;
    }

    private int bfs(Set<String> dict, String beginWord, String endWord) {
        int minLen = 1;
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                String word = queue.poll();
                if(word.equals(endWord))
                    return minLen;
                List<String> neighbors = getNeighbors(dict, word);
                for(String neighbor : neighbors) {
                    queue.offer(neighbor);
                }
            }
            minLen++;
        }
        return 0;
    }

    // O(26 * m)
    private List<String> getNeighbors(Set<String> dict, String word) {
        List<String> neighbors = new ArrayList<>();
        for(int i = 0; i < word.length(); i++) {
            char[] w = word.toCharArray();
            for(char c = 'a'; c <= 'z'; c++) {
                if(w[i] == c)
                    continue;
                w[i] = c;
                String temp = new String(w);
                if(dict.contains(temp)) {
                    neighbors.add(temp);
                    dict.remove(temp);
                }
            }
        }
        return neighbors;
    }
}
