package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.HashMap;

@Problem(
        title = "Largest Values From Labels",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/largest-values-from-labels/"
)
public class Q1090 {
    class Pair {
        int value;
        int label;
        Pair(int value, int label) { this.value = value; this.label = label; }
    }
    public int largestValsFromLabels(int[] values, int[] labels, int num_wanted, int use_limit) {
        int maxSum = 0;
        Pair[] pairs = new Pair[values.length];
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < values.length; i++) {
            pairs[i] = new Pair(values[i], labels[i]);
            int count = Math.min(use_limit, map.getOrDefault(labels[i], 0) + 1);
            map.put(labels[i], count);
        }

        Arrays.sort(pairs, (a, b) -> (b.value - a.value));
        for(Pair p : pairs) {
            int count = map.get(p.label);
            if(count != 0) {
                maxSum += p.value;
                map.put(p.label, count - 1);
                num_wanted--;
            }
            if(num_wanted == 0)
                return maxSum;
        }
        return maxSum;
    }
}
