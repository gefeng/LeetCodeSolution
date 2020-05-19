package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.HashSet;

@Problem(
        title = "Most Common Word",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/most-common-word/"
)
public class Q819 {
    public String mostCommonWord(String paragraph, String[] banned) {
        HashMap<String, Integer> wordMap = new HashMap<>();
        HashSet<String> bannedSet = new HashSet();
        StringBuilder wb = new StringBuilder();

        for(String s : banned) bannedSet.add(s);

        for(int i = 0; i < paragraph.length(); i++) {
            if(isLetter(paragraph.charAt(i))) {
                wb.append(paragraph.charAt(i));
                if(i == paragraph.length() - 1 || !isLetter(paragraph.charAt(i+1))) {
                    String word = wb.toString().toLowerCase();
                    if(!bannedSet.contains(word))
                        wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
                    wb = new StringBuilder();
                }
            }
        }

        int freq = 0;
        String mcWord = "";
        for(String key : wordMap.keySet()) {
            if(wordMap.get(key) > freq) {
                freq = wordMap.get(key);
                mcWord = key;
            }
        }
        return mcWord;
    }
    private boolean isLetter(char c) {
        return (c - 'a' >= 0 && c - 'z' <= 0) || (c - 'A' >= 0 && c - 'Z' <= 0);
    }
}
