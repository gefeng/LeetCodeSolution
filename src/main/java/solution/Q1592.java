package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Rearrange Spaces Between Words",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/rearrange-spaces-between-words/"
)
public class Q1592 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public String reorderSpaces(String text) {
        String[] words = text.trim().split("\\s+");
        int cntLetter = 0;
        int cntSpace = 0;

        for(String w : words) {
            cntLetter += w.length();
        }

        cntSpace = text.length() - cntLetter;

        int space = words.length == 1 ? 0 : cntSpace / (words.length - 1);
        int last = words.length == 1 ? cntSpace : cntSpace % (words.length - 1);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < words.length; i++) {
            sb.append(words[i]);
            if(i < words.length - 1) {
                for(int j = 0; j < space; j++) {
                    sb.append(" ");
                }
            } else {
                for(int j = 0; j < last; j++) {
                    sb.append(" ");
                }
            }
        }

        return sb.toString();
    }
}
