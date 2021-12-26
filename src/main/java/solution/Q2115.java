package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Find All Possible Recipes from Given Supplies",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TOPOLOGICAL_SORT,
        url = "https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies/"
)
public class Q2115 {
    /**
     * Time:  O(E + V)  where V = sizeof(recipes) + sizeof(supplies) E = V * L where L is the number of ingredients for a recipes
     * Space: O(E + V)
     * */
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        List<String> ans = new ArrayList<>();
        int n = recipes.length;

        Set<String> target = new HashSet<>();
        Map<String, List<String>> g = new HashMap<>();
        Map<String, Integer> indegree = new HashMap<>();

        for(int i = 0; i < n; i++) {
            String r = recipes[i];
            List<String> ing = ingredients.get(i);

            target.add(r);
            for(String s : ing) {
                g.computeIfAbsent(s, k -> new ArrayList<>()).add(r);
                indegree.put(r, indegree.getOrDefault(r, 0) + 1);
            }
        }

        Queue<String> q = new ArrayDeque<>();
        for(String s : supplies) {
            q.offer(s);
        }

        while(!q.isEmpty()) {
            String cur = q.poll();
            if(target.contains(cur)) {
                ans.add(cur);
            }

            if(g.containsKey(cur)) {
                for(String nei : g.get(cur)) {
                    int cnt = indegree.getOrDefault(nei, 0);
                    if(cnt == 1) {
                        q.offer(nei);
                    }

                    indegree.put(nei, cnt - 1);
                }
            }
        }

        return ans;
    }
}
