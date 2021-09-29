package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Problem(
        title = "Max Difference You Can Get From Changing an Integer",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/max-difference-you-can-get-from-changing-an-integer/"
)
public class Q1432 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public int maxDiff(int num) {
        int ans = 0;

        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                if(i != j) {
                    int a = replace(num, i, j);
                    if(a == -1) {
                        continue;
                    }
                    set.add(a);
                }
            }
        }

        int max = 0;
        int min = Integer.MAX_VALUE;
        for(int x : set) {
            max = Math.max(max, x);
            min = Math.min(min, x);
        }

        return max - min;
    }

    private int replace(int x, int from, int to) {
        List<Integer> d = new ArrayList<>();
        while(x != 0) {
            d.add(x % 10);
            x /= 10;
        }

        int n = d.size();
        for(int i = 0; i < n; i++) {
            if(d.get(i) == from) {
                d.set(i, to);
            }
        }

        if(d.get(n - 1) == 0) {
            return -1;
        }

        int ans = 0;
        for(int i = n - 1; i >= 0; i--) {
            ans = ans * 10 + d.get(i);
        }
        return ans == 0 ? -1 : ans;
    }
}
