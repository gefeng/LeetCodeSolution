package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.HashSet;

@Problem(
        title = "Vowel Spellchecker",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/vowel-spellchecker/"
)
public class Q966 {
    /*
    * smart way to generate the vowels match map,
    * i.e. apple -> key: *ppl*
    * */
    /**
     * Time:  O(M * L + N * L)
     * Space: O(M * L + N * L)
     * */
    public String[] spellchecker(String[] wordlist, String[] queries) {
        HashSet<String> exaSet = new HashSet<>();
        HashMap<String, String> capMap = new HashMap<>();
        HashMap<String, String> vowMap = new HashMap<>();

        for(String s : wordlist) {
            exaSet.add(s);

            String ls = s.toLowerCase();
            capMap.putIfAbsent(ls, s);
            vowMap.putIfAbsent(replaceVowels(ls), s);
        }

        String[] ans = new String[queries.length];
        for(int i = 0; i < queries.length; i++) {
            String s = queries[i];
            if(exaSet.contains(s))
                ans[i] = s;
            else {
                String ls = s.toLowerCase();
                if(capMap.containsKey(ls))
                    ans[i] = capMap.get(ls);
                else {
                    ans[i] = vowMap.getOrDefault(replaceVowels(ls), "");
                }
            }
        }
        return ans;
    }

    private String replaceVowels(String s) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
                sb.append('*');
            else
                sb.append(c);
        }
        return sb.toString();
    }
}
