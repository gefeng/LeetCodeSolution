package solution.weekly293;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.TreeMap;

@Problem(
        title = "Count Integers in Intervals",
        difficulty = QDifficulty.HARD,
        tag = QTag.ORDERED_MAP,
        url = "https://leetcode.com/contest/weekly-contest-293/problems/count-integers-in-intervals/"
)
public class Q2276 {
    /**
     * Time:  O(logN)
     * Space: O(N)
     * */
    int tot;
    TreeMap<Integer, Integer> map;
    public Q2276() {
        tot = 0;
        map = new TreeMap<>();
    }

    public void add(int left, int right) {
        Integer hk = map.higherKey(left);

        while(hk != null && map.get(hk) < right) {
            tot -= map.get(hk) - hk + 1;
            map.remove(hk);
            hk = map.higherKey(hk);
        }

        Integer fk1 = map.floorKey(left);
        int l = 0;
        int r = 0;

        if(fk1 == null || map.get(fk1) < left) {
            l = left;
            r = right;
        } else {
            l = fk1;
            r = Math.max(map.get(fk1), right);
            tot -= map.get(fk1) - fk1 + 1;
            map.remove(fk1);
        }

        Integer fk2 = map.floorKey(right);
        if(fk2 != null && map.get(fk2) >= right) {
            r = Math.max(r, map.get(fk2));
            tot -= map.get(fk2) - fk2 + 1;
            map.remove(fk2);
        }

        map.put(l, r);
        tot += (r - l + 1);
    }

    public int count() {
        return tot;
    }
}
