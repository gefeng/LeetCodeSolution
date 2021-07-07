package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Problem(
        title = "Count Good Meals",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/count-good-meals/"
)
public class Q1711 {
    private static final int MOD = (int)1e9 + 7;
    public int countPairs(int[] deliciousness) {
        int n = deliciousness.length;
        Set<Integer> powers = new HashSet<>();

        int prod = 1;
        for(int i = 0; i <= 21; i++) {
            powers.add(prod);
            prod *= 2;
        }

        Map<Integer, Integer> cntMap = new HashMap<>();
        int cnt = 0;
        for(int d : deliciousness) {
            for(int p : powers) {
                Integer freq = cntMap.get(p - d);
                cnt = freq == null ? cnt : (cnt + freq) % MOD;
            }
            cntMap.put(d, cntMap.getOrDefault(d, 0) + 1);
        }

        return cnt;
    }
}
