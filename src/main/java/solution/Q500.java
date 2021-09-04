package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Keyboard Row",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/keyboard-row/"
)
public class Q500 {
    /**
     * Time:  O(N * L)
     * Space: O(N * L)
     * */
    public String[] findWords(String[] words) {
        String[] keyboard = new String[] {"qwertyuiop", "asdfghjkl", "zxcvbnm"};

        List<String> res = new ArrayList<>();

        for(String word : words) {
            boolean canType;

            for(String row : keyboard) {
                canType = true;
                for(int i = 0; i < word.length(); i++) {
                    char c = Character.toLowerCase(word.charAt(i));
                    if(row.indexOf(c + "") < 0) {
                        canType = false;
                        break;
                    }
                }

                if(canType) {
                    res.add(word);
                    break;
                }
            }
        }

        return res.toArray(new String[res.size()]);
    }
}
