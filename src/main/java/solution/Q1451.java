package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Rearrange Words in a Sentence",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/rearrange-words-in-a-sentence/"
)
public class Q1451 {
    /**
     * Learned from discussion that Java applies merge sort on Objective type array which
     * is stable (remains the original order if comparator returns 0). Meanwhile primitive type
     * is applied quick sort which is unstable.
     *
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public String arrangeWords(String text) {
        String[] sArr = text.toLowerCase().split(" ");

        Arrays.sort(sArr, Comparator.comparingInt(a -> a.length()));

        String ans = String.join(" ", sArr);

        return Character.toUpperCase(ans.charAt(0)) + ans.substring(1);
    }
}
