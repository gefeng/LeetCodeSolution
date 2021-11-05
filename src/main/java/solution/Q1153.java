package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "String Transforms Into Another String",
        difficulty = QDifficulty.HARD,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/string-transforms-into-another-string/"
)
public class Q1153 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public boolean canConvert(String str1, String str2) {
        int n = str1.length();
        int[] map = new int[26];
        Arrays.fill(map, -1);


        boolean[] used = new boolean[26];
        for(int i = 0; i < n; i++) {
            int c1 = str1.charAt(i) - 'a';
            int c2 = str2.charAt(i) - 'a';

            if(map[c1] != -1 && map[c1] != c2) {
                return false;
            }

            map[c1] = c2;
            used[c2] = true;
        }

        int cntFree = 0;
        for(int i = 0; i < 26; i++) {
            if(!used[i]) {
                cntFree += 1;
            }
        }

        for(int i = 0; i < 26; i++) {
            boolean[] seen = new boolean[26];
            boolean hasCycle = false;
            int len = 0;
            int cur = i;
            seen[cur] = true;
            while(map[cur] != -1) {
                cur = map[cur];

                if(seen[cur]) {
                    hasCycle = true;
                    break;
                }

                seen[cur] = true;
                len += 1;
            }
            if(hasCycle && len != 0 && cntFree == 0) {
                return false;
            }
        }

        return true;
    }
}
