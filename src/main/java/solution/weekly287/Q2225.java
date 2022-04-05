package solution.weekly287;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Problem(
        title = "Find Players With Zero or One Losses",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/contest/weekly-contest-287/problems/find-players-with-zero-or-one-losses/"
)
public class Q2225 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public List<List<Integer>> findWinners(int[][] matches) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();

        int[] lose = new int[100005];
        boolean[] attend = new boolean[100005];

        for(int[] m : matches) {
            attend[m[0]] = true;
            attend[m[1]] = true;
            lose[m[1]]++;
        }

        for(int i = 0; i < 100005; i++) {
            if(!attend[i]) continue;
            if(lose[i] == 0) l1.add(i);
            if(lose[i] == 1) l2.add(i);
        }

        Collections.sort(l1);
        Collections.sort(l2);

        ans.add(l1);
        ans.add(l2);

        return ans;
    }
}
