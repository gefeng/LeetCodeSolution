package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

public class Q937 {
    @Problem(
            title = "Reorder Data in Log Files",
            difficulty = QDifficulty.EASY,
            tag = QTag.STRING,
            url = "https://leetcode.com/problems/reorder-data-in-log-files/"
    )
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (log1, log2) -> {
            String[] split1 = log1.split("\\s", 2);
            String[] split2 = log2.split("\\s", 2);
            boolean isLetter1 = Character.isLetter(split1[1].charAt(0));
            boolean isLetter2 = Character.isLetter(split2[1].charAt(0));
            if(isLetter1 && isLetter2) {
                int cmp = split1[1].compareTo(split2[1]);
                if(cmp != 0) return cmp;
                else return split1[0].compareTo(split2[0]);
            }
            return isLetter1 ? -1 : (isLetter2 ? 1 : 0);
        });
        return logs;
    }
}
