package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.TreeMap;

@Problem(
        title = "Snapshot Array",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/snapshot-array/"
)
public class Q1146 {
    TreeMap<Integer, Integer>[] arr;
    int id = 0;
    public Q1146(int length) {
        arr = new TreeMap[length];
        id = 0;

        for(int i = 0; i < length; i++) {
            arr[i] = new TreeMap<>();
            arr[i].put(0, 0);
        }
    }

    /**
     * Time:  O(logN)
     * */
    public void set(int index, int val) {
        TreeMap<Integer, Integer> map = arr[index];
        map.put(id, val);
    }

    /**
     * Time:  O(1)
     * */
    public int snap() {
        id++;
        return id - 1;
    }

    /**
     * Time: O(logN)
     * */
    public int get(int index, int snap_id) {
        return arr[index].floorEntry(snap_id).getValue();
    }
}
