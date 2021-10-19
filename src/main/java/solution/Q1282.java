package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Problem(
        title = "Group the People Given the Group Size They Belong To",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/group-the-people-given-the-group-size-they-belong-to/"
)
public class Q1282 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        List<List<Integer>> ans = new ArrayList<>();

        int n = groupSizes.length;

        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            map.computeIfAbsent(groupSizes[i], k -> new ArrayList<>()).add(i);
        }

        for(int k : map.keySet()) {
            List<Integer> e = map.get(k);
            int tot = e.size();

            List<Integer> g = null;
            for(int i = 0; i < tot; i++) {
                if(i % k == 0) {
                    g = new ArrayList<>();
                }
                g.add(e.get(i));
                if(i % k == k - 1) {
                    ans.add(g);
                }
            }
        }

        return ans;
    }
}
