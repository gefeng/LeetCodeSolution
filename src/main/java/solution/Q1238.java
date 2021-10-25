package solution;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Q1238 {
    /**
     * Time:  O(N!) should be much lower.
     * Space: O(2 ^ N)
     * */
    public List<Integer> circularPermutation(int n, int start) {
        List<Integer> ans = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();

        ans.add(start);
        seen.add(start);
        dfs(n, start, start, ans, seen);

        return ans;
    }

    private boolean dfs(int n, int start, int pre, List<Integer> ans, Set<Integer> seen) {
        if(ans.size() == (1 << n)) {
            if(Integer.bitCount(start ^ pre) == 1) {
                return true;
            }
            return false;
        }

        for(int i = 0; i < n; i++) {
            int cur = pre ^ (1 << i);
            if(seen.contains(cur)) {
                continue;
            }

            seen.add(cur);
            ans.add(cur);
            if(dfs(n, start, cur, ans, seen)) {
                return true;
            }
            seen.remove(cur);
            ans.remove(ans.size() - 1);
        }

        return false;
    }
}
