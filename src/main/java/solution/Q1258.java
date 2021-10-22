package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Synonymous Sentences",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/synonymous-sentences/"
)
public class Q1258 {
    /**
     * Time:  O(N ^ M)
     * Space: O(N + M)
     * */
    public List<String> generateSentences(List<List<String>> synonyms, String text) {
        Map<String, List<String>> map = new HashMap<>();

        for(List<String> p : synonyms) {
            String s1 = p.get(0);
            String s2 = p.get(1);

            map.computeIfAbsent(s1, k -> new ArrayList<>()).add(s2);
            map.computeIfAbsent(s2, k -> new ArrayList<>()).add(s1);
        }

        List<Set<String>> g = new ArrayList<>();
        for(String s : map.keySet()) {
            boolean solve = true;
            for(Set<String> set : g) {
                if(set.contains(s)) {
                    solve = false;
                    break;
                }
            }

            if(solve) {
                Set<String> set = new HashSet<>();
                dfs1(map, s, set);
                g.add(set);
            }
        }

        //System.out.println(g.size());

        String[] words = text.split(" ");
        List<String> ans = new ArrayList<>();
        dfs2(words, 0, g, new ArrayList<>(), ans);

        Collections.sort(ans);
        return ans;
    }

    private void dfs1(Map<String, List<String>> map, String cur, Set<String> path) {
        path.add(cur);

        for(String nei : map.get(cur)) {
            if(!path.contains(nei)) {
                dfs1(map, nei, path);
            }
        }
    }

    private void dfs2(String[] words, int cur, List<Set<String>> g, List<String> cand, List<String> ans) {
        if(cur == words.length) {
            ans.add(String.join(" ", cand));
            return;
        }

        String s = words[cur];

        Set<String> rep = null;
        for(Set<String> set : g) {
            if(set.contains(s)) {
                rep = set;
                break;
            }
        }

        if(rep == null) {
            cand.add(s);
            dfs2(words, cur + 1, g, cand, ans);
            cand.remove(cand.size() - 1);
        } else {
            for(String r : rep) {
                cand.add(r);
                dfs2(words, cur + 1, g, cand, ans);
                cand.remove(cand.size() - 1);
            }
        }
    }
}
