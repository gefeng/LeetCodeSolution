package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Groups of Strings",
        difficulty = QDifficulty.HARD,
        tag = QTag.UNION_FIND,
        url = "https://leetcode.com/problems/groups-of-strings/"
)
public class Q2157 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int[] groupStrings(String[] words) {
        int n = words.length;
        DJSet djs = new DJSet(n);
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < n; i++) {
            int mask = toMask(words[i]);

            if(map.containsKey(mask)) {
                djs.union(i, map.get(mask));
            } else {
                map.put(mask, i);
            }
        }

        for(int i = 0; i < n; i++) {
            int mask = toMask(words[i]);

            for(int j = 0; j < 26; j++) {
                // add
                if((mask & (1 << j)) == 0) {
                    int nmask = mask | (1 << j);
                    if(map.containsKey(nmask)) {
                        djs.union(i, map.get(nmask));
                    }
                }

                // delete
                if((mask & (1 << j)) != 0) {
                    int nmask = mask ^ (1 << j);
                    if(map.containsKey(nmask)) {
                        djs.union(i, map.get(nmask));
                    }
                }

                if((mask & (1 << j)) != 0) {
                    for(int k = 0; k < 26; k++) {
                        if(k == j || (mask & (1 << k)) != 0) continue;

                        int nmask = mask ^ (1 << j);
                        nmask = nmask | (1 << k);

                        if(map.containsKey(nmask)) {
                            djs.union(i, map.get(nmask));
                        }
                    }
                }
            }
        }

        return new int[] {djs.groups(), djs.max()};
    }

    private int toMask(String s) {
        int mask = 0;
        int n = s.length();
        for(int i = 0; i < n; i++) {
            mask |= 1 << s.charAt(i) - 'a';
        }
        return mask;
    }

    private class DJSet {
        int[] p;
        int[] w;
        DJSet(int n) {
            p = new int[n];
            w = new int[n];
            Arrays.fill(p, -1);
            Arrays.fill(w, 1);
        }

        int find(int i) {
            if(p[i] < 0) return i;
            return p[i] = find(p[i]);
        }

        void union(int i, int j) {
            int x = find(i);
            int y = find(j);
            if(x == y) return;
            if(w[x] >= w[y]) {
                p[y] = x;
                w[x] += w[y];
            } else {
                p[x] = y;
                w[y] += w[x];
            }
        }

        int groups() {
            int cnt = 0;
            for(int i = 0; i < p.length; i++) {
                if(p[i] < 0) cnt++;
            }
            return cnt;
        }

        int max() {
            int max = 0;
            int n = p.length;
            int[] cnt = new int[n];
            for(int i = 0; i < n; i++) {
                int x = find(i);
                cnt[x]++;
                max = Math.max(max, cnt[x]);
            }

            return max;
        }
    }
}
