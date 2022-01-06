package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Cracking the Safe",
        difficulty = QDifficulty.HARD,
        tag = QTag.EULERIAN_CIRCUIT,
        url = "https://leetcode.com/problems/cracking-the-safe/"
)
public class Q753 {
    /**
     * Time:  O(K ^ (N - 1) + K ^ (N - 1) * K)
     * Space: O(K ^ (N - 1) * K)
     * */
    public String crackSafe(int n, int k) {
        List<String> all = new ArrayList<>();
        gen(n - 1, k, new StringBuilder(), all);

        Map<String, Queue<Character>> g = new HashMap<>();
        build(g, all, n - 1, k);

        String first = "";
        for(int i = 0; i < n - 1; i++) {
            first += "0";
        }

        StringBuilder cycle = new StringBuilder();
        eulCyc(g, first, cycle);

        String ans = cycle.toString();

        return ans + first;
    }

    private void gen(int n, int k, StringBuilder cand, List<String> all) {
        if(cand.length() == n) {
            all.add(cand.toString());
            return;
        }

        for(int i = 0; i < k; i++) {
            cand.append(i);
            gen(n, k, cand, all);
            cand.deleteCharAt(cand.length() - 1);
        }
    }

    private void build(Map<String, Queue<Character>> g, List<String> all, int n, int k) {
        for(String s : all) {
            Queue<Character> out = new ArrayDeque<>();

            for(char c = '0'; c < '0' + k; c++) {
                out.offer(c);
            }

            g.put(s, out);
        }
    }

    private void eulCyc(Map<String, Queue<Character>> g, String cur, StringBuilder cycle) {
        Queue<Character> out = g.get(cur);
        while(!out.isEmpty()) {
            char c = out.poll();
            String next = (cur + c).substring(1);
            eulCyc(g, next, cycle);
            cycle.append(c);
        }
    }
}
