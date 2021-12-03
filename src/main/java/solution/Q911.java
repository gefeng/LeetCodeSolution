package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Problem(
        title = "Online Election",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/online-election/"
)
public class Q911 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    TreeMap<Integer, Integer> map;
    public Q911(int[] persons, int[] times) {
        map = new TreeMap<>();

        int n = persons.length;
        int winner = 0;
        int[] cnt = new int[n + 1];

        for(int i = 0; i < n; i++) {
            int p = persons[i];
            int t = times[i];

            if(++cnt[p] >= cnt[winner]) {
                winner = p;
            }

            map.put(t, winner);
        }
    }

    /**
     * Time:  O(logN)
     * */
    public int q(int t) {
        Integer fk = map.floorKey(t);

        if(fk != null) {
            return map.get(fk);
        }
        return -1;
    }
}
