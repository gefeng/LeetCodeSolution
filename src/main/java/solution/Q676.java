package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Implement Magic Dictionary",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/implement-magic-dictionary/"
)
public class Q676 {
    Set<String> set;
    public Q676() {
        set = new HashSet<>();
    }

    public void buildDict(String[] dictionary) {
        for(String w : dictionary) {
            set.add(w);
        }
    }

    /**
     * Time:  O(N * L)
     * */
    public boolean search(String searchWord) {
        int n = searchWord.length();

        for(String s : set) {
            if(s.length() == n) {
                int dif = 0;
                for(int i = 0; i < n; i++) {
                    if(s.charAt(i) != searchWord.charAt(i)) dif++;
                }

                if(dif == 1) return true;
            }
        }

        return false;
    }
}
