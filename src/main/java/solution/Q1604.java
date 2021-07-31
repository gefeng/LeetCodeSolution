package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Alert Using Same Key-Card Three or More Times in a One Hour Period",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/alert-using-same-key-card-three-or-more-times-in-a-one-hour-period/"
)
public class Q1604 {
    /**
     * M: number of people
     * Time:  O(N + M * (log(N / M) + N / M))
     * Space: O(N)
     * */
    public List<String> alertNames(String[] keyName, String[] keyTime) {
        int n = keyName.length;
        List<String> res = new ArrayList<>();
        Map<String, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            List<Integer> times = map.computeIfAbsent(keyName[i], k -> new ArrayList<>());
            int h = Integer.valueOf(keyTime[i].substring(0, 2));
            int m = Integer.valueOf(keyTime[i].substring(3, 5));
            times.add(h * 60 + m);
        }

        for (Map.Entry<String, List<Integer>> e : map.entrySet()) {
            String name = e.getKey();
            List<Integer> times = e.getValue();

            Collections.sort(times);

            for (int l = 0, r = 0; r < times.size(); r++) {
                while (times.get(r) - times.get(l) > 60) {
                    l++;
                }

                if (r - l + 1 > 2) {
                    res.add(name);
                    break;
                }
            }
        }

        Collections.sort(res);

        return res;
    }
}
