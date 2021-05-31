package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Sentence Similarity III",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/sentence-similarity-iii/"
)
public class Q1813 {
    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        String[] word1 = sentence1.split("\\s");
        String[] word2 = sentence2.split("\\s");
        int m = word1.length;
        int n = word2.length;

        if(m < n) {
            return areSentencesSimilar(sentence2, sentence1);
        }

        if(n == 0) {
            return true;
        }


        int comPrefix = 0;
        int comSuffix = 0;
        int i = 0;
        while(i < m && i < n && word1[i].equals(word2[i])) {
            comPrefix++;
            i++;
        }
        i = m - 1;
        int j = n - 1;
        while(i >= 0 && j >= 0 && word1[i].equals(word2[j])) {
            comSuffix++;
            i--;
            j--;
        }

        return comPrefix + comSuffix == n || comPrefix == n || comSuffix == n;
    }
}
