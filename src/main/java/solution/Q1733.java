package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Problem(
        title = "Minimum Number of People to Teach",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/minimum-number-of-people-to-teach/"
)
public class Q1733 {
    /*
    * brute force. Test each language to find out number of users to teach.
    * */
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        int m = languages.length;
        Set<Integer>[] lang = new Set[m + 1];

        for(int i = 1; i <= m; i++) {
            lang[i] = new HashSet<>();
            for(int l : languages[i - 1]) {
                lang[i].add(l);
            }
        }

        // remove friendships with common languages
        List<int[]> learn = new ArrayList<>();
        for(int[] f : friendships) {
            boolean common = false;
            for(int l : lang[f[0]]) {
                if(lang[f[1]].contains(l)) {
                    common = true;
                    break;
                }
            }
            if(!common) {
                learn.add(f);
            }
        }

        int min = Integer.MAX_VALUE;
        for(int i = 1; i <= n; i++) {
            Set<Integer> set = new HashSet<>();
            int cnt = 0;
            for(int[] f : learn) {
                if(!set.contains(f[0]) && !lang[f[0]].contains(i)) {
                    set.add(f[0]);
                    cnt++;
                }
                if(!set.contains(f[1]) && !lang[f[1]].contains(i)) {
                    set.add(f[1]);
                    cnt++;
                }
            }
            min = Math.min(min, cnt);
        }

        return min;
    }
}
