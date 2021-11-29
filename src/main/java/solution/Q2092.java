package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Problem(
        title = "Find All People With Secret",
        difficulty = QDifficulty.HARD,
        tag = QTag.UNION_FIND,
        url = "https://leetcode.com/problems/find-all-people-with-secret/"
)
public class Q2092 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        boolean[] know = new boolean[n];
        List<Integer> ans = new ArrayList<>();
        DSU dsu = new DSU(n);
        Map<Integer, List<int[]>> map = new TreeMap<>();

        know[0] = true;
        know[firstPerson] = true;

        for(int[] m : meetings) {
            List<int[]> e = map.computeIfAbsent(m[2], k -> new ArrayList<>());
            e.add(new int[] {m[0], m[1]});
        }

        for(int k : map.keySet()) {
            List<int[]> e = map.get(k);

            for(int[] p : e) {
                dsu.union(p[0], p[1]);
                if(know[p[0]] || know[p[1]]) {
                    know[dsu.find(p[0])] = true;
                }
            }

            for(int[] p : e) {
                if(know[dsu.find(p[0])]) {
                    know[p[0]] = true;
                    know[p[1]] = true;
                }
            }

            for(int[] p : e) {
                dsu.set(p[0], p[0]);
                dsu.set(p[1], p[1]);
            }
        }


        for(int i = 0; i < n; i++) {
            if(know[i]) {
                ans.add(i);
            }
        }

        return ans;
    }

    private class DSU {
        int[] p;
        int[] w;
        DSU(int n) {
            p = new int[n];
            w = new int[n];
            for(int i = 0; i < n; i++) {
                p[i] = i;
            }
        }
        int find(int i) {
            if(p[i] != i) p[i] = find(p[i]);
            return p[i];
        }
        void union(int i, int j) {
            int x = find(i);
            int y = find(j);
            if(x == y) return;
            if(w[x] == w[y]) {
                p[y] = x;
                w[x]++;
            } else if(w[x] > w[y]) {
                p[y] = x;
            } else {
                p[x] = y;
            }
        }
        void set(int i, int x) {
            p[i] = x;
        }
    }
}
