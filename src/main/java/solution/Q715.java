package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.TreeMap;

@Problem(
        title = "Range Module",
        difficulty = QDifficulty.HARD,
        tag = QTag.DESIGN,
        url = "https://leetcode.com/problems/range-module/"
)
public class Q715 {
    TreeMap<Integer, Integer> map;
    public Q715() {
        map = new TreeMap<>();
    }

    /**
     * Time:  O(N)
     * */
    public void addRange(int left, int right) {
        int cur = left;
        while(true) {
            Integer ck = map.ceilingKey(cur);
            if(ck == null) break;

            int x = ck;
            int y = map.get(ck);

            if(y <= right) map.remove(ck);
            else break;

            cur = y;
        }

        Integer fk1 = map.floorKey(left);
        if(fk1 != null) {
            int x = fk1;
            int y = map.get(fk1);

            if(y >= right) return;

            if(y >= left) {
                map.remove(fk1);
                left = fk1;
            }
        }

        Integer fk2 = map.floorKey(right);
        if(fk2 != null) {
            int x = fk2;
            int y = map.get(fk2);
            if(y > right) {
                map.remove(fk2);
                right = y;
            }
        }

        map.put(left, right);
    }

    /**
     * Time:  O(logN)
     * */
    public boolean queryRange(int left, int right) {
        Integer fk = map.floorKey(left);
        boolean ans = false;
        if(fk != null) {
            int x = fk;
            int y = map.get(fk);

            if(y >= right) ans = true;
        }

        return ans;
    }

    /**
     * Time:  O(N)
     * */
    public void removeRange(int left, int right) {
        int cur = left;
        while(true) {
            Integer ck = map.ceilingKey(cur);
            if(ck == null) break;

            int x = ck;
            int y = map.get(ck);

            if(y <= right) map.remove(ck);
            else break;

            cur = y;
        }

        Integer fk1 = map.floorKey(left);
        if(fk1 != null) {
            int x = fk1;
            int y = map.get(fk1);
            if(y > right) {
                map.put(fk1, left);
                map.put(right, y);
                return;
            }
            if(y > left) {
                map.put(fk1, left);
            }
        }

        Integer fk2 = map.floorKey(right);
        if(fk2 != null) {
            int x = fk2;
            int y = map.get(fk2);
            if(x < right && y > right) {
                map.remove(fk2);
                map.put(right, y);
            }
        }
    }
}
