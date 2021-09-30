package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Display Table of Food Orders in a Restaurant",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/display-table-of-food-orders-in-a-restaurant/"
)
public class Q1418 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public List<List<String>> displayTable(List<List<String>> orders) {
        List<List<String>> ans = new ArrayList<>();
        Set<String> foods = new TreeSet<>();
        Map<Integer, Map<String, Integer>> map = new TreeMap<>();

        for(List<String> l : orders) {
            int t = Integer.parseInt(l.get(1));
            String f = l.get(2);

            foods.add(f);
            Map<String, Integer> e = map.computeIfAbsent(t, k -> new HashMap<>());
            e.put(f, e.getOrDefault(f, 0) + 1);
        }

        ans.add(new ArrayList<>());
        ans.get(0).add("Table");
        for(String f : foods) {
            ans.get(0).add(f);
        }

        int sz = ans.get(0).size();
        for(int key : map.keySet()) {
            List<String> row = new ArrayList<>();
            row.add(Integer.toString(key));

            Map<String, Integer> e = map.get(key);
            for(int i = 1; i < sz; i++) {
                String f = ans.get(0).get(i);
                row.add(Integer.toString(e.getOrDefault(f, 0)));
            }

            ans.add(row);
        }

        return ans;
    }
}
