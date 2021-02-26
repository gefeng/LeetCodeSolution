package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Alien Dictionary",
        difficulty = QDifficulty.HARD,
        tag = QTag.TOPOLOGICAL_SORT,
        url = "https://leetcode.com/problems/alien-dictionary/"
)
public class Q269 {
    /*
    *   n = words.length
    *   m = Math.max(word.length)
    *   time: O(n * m)
    *   space: O(26 * 26) -> O(1)
    */
    public String alienOrder(String[] words) {
        HashMap<Character, Set<Character>> graph = new HashMap<>();
        int[] degree = new int[26];
        Arrays.fill(degree, -1);

        // O(m * n)
        for(String word : words) {
            for(int i = 0; i < word.length(); i++)
                degree[word.charAt(i) - 'a'] = 0;
        }

        // build graph  O(n * m)
        for(int i = 0; i < words.length - 1; i++) {
            String wordA = words[i];
            String wordB = words[i + 1];
            if(!insertEdge(graph, degree, wordA, wordB))
                return "";
        }
        /*for(int i = 0; i < words.length - 1; i++) {
            String wordA = words[i];
            for(int j = i + 1; j < words.length; j++) {
                String wordB = words[j];
                if(!insertEdge(graph, degree, wordA, wordB))
                    return "";
            }
        }*/

        // bfs remove edge O(1)
        Queue<Character> queue = new LinkedList<>();
        int numLetters = 0;
        for(int i = 0; i < degree.length; i++) {
            if(degree[i] != -1)
                numLetters++;
            if(degree[i] == 0)
                queue.offer((char)(i + 'a'));
        }

        // O(26 * 26)
        StringBuilder ans = new StringBuilder();
        while(!queue.isEmpty()) {
            char letter = queue.poll();
            ans.append(letter);

            if(!graph.containsKey(letter))
                continue;

            for(char neighbor : graph.get(letter)) {
                degree[neighbor - 'a']--;
                if(degree[neighbor - 'a'] == 0)
                    queue.offer(neighbor);
            }
        }

        if(ans.length() != numLetters)
            return "";
        return ans.toString();
    }

    private boolean insertEdge(HashMap<Character, Set<Character>> graph, int[] degree, String wordA, String wordB) {
        int lenA = wordA.length();
        int lenB = wordB.length();
        int i = 0;
        while(i < lenA && i < lenB) {
            char x = wordA.charAt(i);
            char y = wordB.charAt(i);

            if(x != y) {
                if(!graph.containsKey(x))
                    graph.put(x, new HashSet<>());
                if(!graph.get(x).contains(y)) {
                    graph.get(x).add(y);
                    degree[y - 'a']++;
                }
                return true;
            }
            i++;
        }

        return lenA <= lenB;
    }
}
