package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

@Problem(
        title = "Stock Price Fluctuation",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/stock-price-fluctuation/"
)
public class Q2034 {
    /**
     * Time:  O(logN)
     * Space: O(N)
     * */
    TreeMap<Integer, Integer> ttp;
    TreeMap<Integer, Set<Integer>> ptt;
    public Q2034() {
        ttp = new TreeMap<>();
        ptt = new TreeMap<>();
    }

    public void update(int timestamp, int price) {
        if(ttp.containsKey(timestamp)) {
            int old = ttp.get(timestamp);

            Set<Integer> t = ptt.get(old);
            t.remove(timestamp);
            if(t.isEmpty()) {
                ptt.remove(old);
            }
        }

        ttp.put(timestamp, price);
        ptt.computeIfAbsent(price, k -> new HashSet<>()).add(timestamp);
    }

    public int current() {
        return ttp.get(ttp.lastKey());
    }

    public int maximum() {
        return ptt.lastKey();
    }

    public int minimum() {
        return ptt.firstKey();
    }
}
