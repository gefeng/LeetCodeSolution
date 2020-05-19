package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "To Lower Case",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING
)
public class Q709 {
    public String toLowerCase(String str) {
        char[] chars = str.toCharArray();

        for(int i = 0; i < chars.length; i++) {
            if(chars[i] >= 'A' && chars[i] <= 'Z')
                chars[i] = (char)(chars[i] - 'A' + 'a');
        }

        return new String(chars);
    }
}
