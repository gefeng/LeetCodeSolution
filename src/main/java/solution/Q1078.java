package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Occurrences After Bigram",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/occurrences-after-bigram/"
)
public class Q1078 {
    /**
     * Time:  O(N * L)
     * Space: O(N * L)
     * */
    public String[] findOcurrences(String text, String first, String second) {
        String[] arr = text.split("\\s+");
        List<String> ans = new ArrayList<>();

        for(int i = 2; i < arr.length; i++) {
            if(arr[i - 2].equals(first) && arr[i - 1].equals(second)) {
                ans.add(arr[i]);
            }
        }

        return ans.toArray(new String[ans.size()]);
    }
}
