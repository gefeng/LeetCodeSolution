package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Longest Common Prefix",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING
)
public class Q14 {
    public String longestCommonPrefix(String[] strs) {
        String commonPrefix = "";
        String currentPrefix = "";
        int index = 0;

        if(strs.length == 0)
            return commonPrefix;

        for (String str : strs) {
            if(str.isEmpty())
                return commonPrefix;
        }

        while(true) {
            if(index < strs[0].length()) {
                currentPrefix = strs[0].substring(0, ++index);
            }
            else
                return commonPrefix;
            for (String str : strs) {
                if (str.indexOf(currentPrefix, 0) != 0)
                    return commonPrefix;
            }
            commonPrefix = currentPrefix;
        }
    }
}
