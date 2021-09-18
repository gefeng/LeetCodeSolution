package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Find Original Array From Doubled Array",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/find-original-array-from-doubled-array/"
)
public class Q2007 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public int[] findOriginalArray(int[] changed) {
        int n = changed.length;
        if(n % 2 != 0) {
            return new int[0];
        }

        int[] ans = new int[n / 2];

        Arrays.sort(changed);

        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++) {
            map.put(changed[i], map.getOrDefault(changed[i], 0) + 1);
        }

        int j = 0;
        for(int i = 0; i < n; i++) {
            int x = changed[i];

            if(!map.containsKey(x)) {
                continue;
            } else {
                int cnt1 = map.get(x);
                if(cnt1 == 1) {
                    map.remove(x);
                } else {
                    map.put(x, cnt1 - 1);
                }

                if(map.containsKey(x * 2)) {
                    int cnt2 = map.get(x * 2);
                    if(cnt2 == 1) {
                        map.remove(x * 2);
                    } else {
                        map.put(x * 2, cnt2 - 1);
                    }

                    ans[j++] = x;
                } else {
                    break;
                }
            }
        }

        if(j != n / 2) {
            return new int[0];
        }
        return ans;
    }
}
