package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

public class Q126 {
    @Problem(
            title = "Word Ladder II",
            difficulty = QDifficulty.HARD,
            tag = QTag.BREATH_FIRST_SEARCH,
            url = "https://leetcode.com/problems/word-ladder-ii/"
    )
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ans = new ArrayList<>();

        HashSet<String> dict = new HashSet<>();
        for(String word : wordList)
            dict.add(word);

        Queue<List<String>> queue = new LinkedList<>();
        List<String> initPath = new ArrayList<>();
        initPath.add(beginWord);
        queue.offer(initPath);

        while(!queue.isEmpty() && ans.size() == 0) {
            int size = queue.size();
            Set<String> visited = new HashSet<>();
            for(int i = 0; i < size; i++) {
                List<String> path = queue.poll();
                String word = path.get(path.size() - 1);
                if(word.equals(endWord)) {
                    ans.add(path);
                }
                else {
                    List<String> neighbors = getNeighbors(dict, word);
                    for(String neighbor : neighbors) {
                        List<String> newPath = new ArrayList<>(path);
                        newPath.add(neighbor);
                        visited.add(neighbor);
                        queue.offer(newPath);
                    }
                }
            }
            for(String word : visited)
                dict.remove(word);
        }

        return ans;
    }

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
                }
            }
        }
        return neighbors;
    }
}
