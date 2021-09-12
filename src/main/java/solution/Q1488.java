package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

@Problem(
        title = "Avoid Flood in The City",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/avoid-flood-in-the-city/"
)
public class Q1488 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] ans = new int[n];
        Arrays.fill(ans, 1);

        boolean canAvoid = true;
        TreeSet<Integer> dry = new TreeSet<>();
        Map<Integer, Integer> lakes = new HashMap<>();

        for(int i = 0; i < n; i++) {
            int l = rains[i];
            if(l > 0) {
                ans[i] = -1;

                if(lakes.containsKey(l)) {
                    int pre = lakes.get(l);

                    Integer day = dry.ceiling(pre);

                    if(day == null) {
                        canAvoid = false;
                        break;
                    } else {
                        ans[day] = l;
                        dry.remove(day);
                        lakes.put(l, i);
                    }
                } else {
                    lakes.put(l, i);
                }
            } else {
                dry.add(i);
            }
        }

        if(canAvoid) {
            return ans;
        }
        return new int[0];
    }
}
