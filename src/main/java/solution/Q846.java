package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.TreeMap;

@Problem(
        title = "Hand of Straights",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/hand-of-straights/"
)
public class Q846 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public boolean isNStraightHand(int[] hand, int groupSize) {
        int n = hand.length;

        if(n % groupSize != 0) return false;

        TreeMap<Integer, Integer> map = new TreeMap<>();

        for(int c : hand) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        while(!map.isEmpty()) {
            int cnt = 0;
            int pre = -1;
            int cur = -1;
            while(cnt != groupSize) {
                if(pre == -1) {
                    cur = map.firstKey();
                } else {
                    if(!map.containsKey(pre + 1)) return false;

                    cur = pre + 1;
                }

                int f = map.get(cur);
                if(f == 1) map.remove(cur);
                else map.put(cur, f - 1);

                pre = cur;
                cnt++;
            }
        }

        return true;
    }
}
