package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Find Common Characters",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/find-common-characters/"
)
public class Q1002 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public List<String> commonChars(String[] words) {
        List<String> ans = new ArrayList<>();

        int[] cnt = new int[26];
        for(int i = 0; i < words[0].length(); i++) {
            cnt[words[0].charAt(i) - 'a']++;
        }

        for(int i = 1; i < words.length; i++) {
            String w = words[i];
            int[] temp = new int[26];

            for(int j = 0; j < w.length(); j++) {
                temp[w.charAt(j) - 'a']++;
            }

            for(int j = 0; j < 26; j++) {
                cnt[j] = Math.min(cnt[j], temp[j]);
            }
        }

        for(int i = 0; i < 26; i++) {
            while(cnt[i] != 0) {
                ans.add((char)(i + 'a') + "");
                cnt[i]--;
            }
        }

        return ans;
    }
}
