package solution.weekly297;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Naming a Company",
        difficulty = QDifficulty.HARD,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/contest/weekly-contest-297/problems/naming-a-company/"
)
public class Q2306 {
    /**
     * Time:  O(26 * 26 * N)
     * Space: O(N * M)
     * */
    public long distinctNames(String[] ideas) {
        int n = ideas.length;
        long ans = 0;
        Set<String>[] map = new Set[26];

        for(int i = 0; i < 26; i++) {
            map[i] = new HashSet<>();
        }

        for(String s : ideas) {
            map[s.charAt(0) - 'a'].add(s.substring(1, s.length()));
        }

        for(int i = 0; i < 26; i++) {
            for(int j = 0; j < 26; j++) {
                int cnt = 0; // count # intersections between two sets of suffix.
                for(String suffix : map[i]) {
                    if(map[j].contains(suffix)) {
                        cnt++;
                    }
                }

                ans += (long)(map[i].size() - cnt) * (map[j].size() - cnt);
            }
        }

        return ans;
    }
}
