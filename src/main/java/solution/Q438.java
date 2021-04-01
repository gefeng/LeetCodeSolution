package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Find All Anagrams in a String",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/find-all-anagrams-in-a-string/"
)
public class Q438 {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int[] pCount = new int[26];
        int[] sCount = new int[26];
        int l = 0;
        int r = 0;

        if(s.length() < p.length())
            return ans;

        for(int i = 0; i < p.length(); i++)
            pCount[p.charAt(i) - 'a']++;

        for(; r < s.length(); r++) {
            char c = s.charAt(r);
            sCount[c - 'a']++;
            if(r - l == p.length() - 1) {
                if(isAnagram(pCount, sCount))
                    ans.add(l);
                sCount[s.charAt(l++) - 'a']--;
            }
        }

        return ans;
    }

    private boolean isAnagram(int[] pCount, int[] sCount) {
        for(int i = 0; i < pCount.length; i++) {
            if(pCount[i] != sCount[i])
                return false;
        }
        return true;
    }
}
