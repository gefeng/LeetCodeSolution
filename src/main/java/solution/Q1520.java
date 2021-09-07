package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Maximum Number of Non-Overlapping Substrings",
        difficulty = QDifficulty.HARD,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/maximum-number-of-non-overlapping-substrings/"
)
public class Q1520 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        List<String> res = new ArrayList<>();

        int[][] range = new int[26][2];
        for(int i = 0; i < 26; i++) {
            Arrays.fill(range[i], -1);
        }

        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if(range[c - 'a'][0] == -1) {
                range[c - 'a'][0] = i;
                range[c - 'a'][1] = i;
            } else {
                range[c - 'a'][1] = i;
            }
        }

        List<int[]> cand = new ArrayList<>();
        for(int i = 0; i < 26; i++) {
            int[] cur = range[i];
            if(cur[0] == -1) {
                continue;
            }

            int l = cur[0];
            int r = cur[1];
            for(int j = l; j <= r; j++) {
                int c = s.charAt(j) - 'a';
                if(range[c][0] < l) {
                    r = -1;
                    break;
                }
                r = Math.max(r, range[c][1]);
            }

            if(r != -1) {
                cand.add(new int[] {l, r});
            }
        }

        Collections.sort(cand, Comparator.comparingInt(a -> a[1] - a[0]));

        for(int i = 0; i < cand.size(); i++) {
            int[] r1 = cand.get(i);
            boolean overlap = false;
            for(int j = 0; j < i; j++) {
                int[] r2 = cand.get(j);
                if(r1[0] < r2[1] && r1[1] > r2[1]) {
                    overlap = true;
                    break;
                }
            }

            if(!overlap) {
                res.add(s.substring(r1[0], r1[1] + 1));
            }
        }

        return res;
    }
}
