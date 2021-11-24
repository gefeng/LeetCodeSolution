package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;

@Problem(
        title = "Prison Cells After N Days",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/prison-cells-after-n-days/"
)
public class Q957 {
    /**
     * Time:  O(prefix + cycle)
     * Space: O(prefix + cycle)
     * */
    public int[] prisonAfterNDays(int[] cells, int n) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int cycle = 0;
        int stepsLeft = 0;
        for(int i = 0; i < n; i++) {
            int num = toNumber(cells);
            if(!map.containsKey(num))
                map.put(num, i);
            else {
                int prev = map.get(num);
                cycle = i - prev;
                stepsLeft = (n - prev) % cycle;
                break;
            }
            forward(cells);
        }

        while(stepsLeft != 0) {
            forward(cells);
            stepsLeft--;
        }

        return cells;
    }

    private void forward(int[] cells) {
        int len = cells.length;
        int prev = cells[0];
        for(int i = 1; i < len - 1; i++) {
            int temp = cells[i];
            cells[i] = prev == cells[i + 1] ? 1 : 0;
            prev = temp;
        }
        cells[0] = 0;
        cells[len - 1] = 0;
    }

    private int toNumber(int[] cells) {
        int res = 0;
        for(int i = cells.length - 1; i>= 0; i--)
            res = (res << 1) + cells[i];
        return res;
    }
}
