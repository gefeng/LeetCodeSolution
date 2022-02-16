package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Count Ways to Build Rooms in an Ant Colony",
        difficulty = QDifficulty.HARD,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/count-ways-to-build-rooms-in-an-ant-colony/"
)
public class Q1916 {
    private static final long MOD = (long)1e9 + 7;
    long[] per;
    public int waysToBuildRooms(int[] prevRoom) {
        int n = prevRoom.length;
        List<Integer>[] g = new List[n];
        per = new long[n + 1];

        for(int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }

        for(int i = 0; i < n; i++) {
            if(prevRoom[i] != -1) {
                g[prevRoom[i]].add(i);
            }
        }

        per[0] = 1;
        for(int i = 1; i <= n; i++) {
            per[i] = per[i - 1] * i % MOD;
        }

        return (int)dfs(g, 0)[1];
    }

    private long[] dfs(List<Integer>[] g, int cur) {
        List<Integer> neis = g[cur];

        if(neis.isEmpty()) {
            return new long[] {1, 1};
        }

        long cnt = 0;
        long res = 0;
        List<long[]> l = new ArrayList<>();
        for(int nei : neis) {
            long[] ret = dfs(g, nei);
            cnt += ret[0];
            l.add(ret);
        }

        res = per[(int)cnt];
        for(long[] s : l) {
            res = res * inv(per[(int)s[0]]) % MOD;
        }

        for(long[] s : l) {
            res = (res * s[1]) % MOD;
        }

        return new long[] { (cnt + 1) % MOD, res};
    }

    /**
     * Inverse modular (modular division)
     * */
    long inv(long x) {
        long s = 1;
        for (; x > 1; x = MOD % x)
            s = s * (MOD - MOD / x) % MOD;
        return s;
    }
}
