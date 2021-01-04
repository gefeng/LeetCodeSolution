package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Compare Version Numbers",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/compare-version-numbers/"
)
public class Q165 {
    public int compareVersion(String version1, String version2) {
        String[] revision1 = version1.split("\\.");
        String[] revision2 = version2.split("\\.");

        for(int i = 0; i < revision1.length || i < revision2.length; i++) {
            String rStr1 = i < revision1.length ? revision1[i] : "0";
            String rStr2 = i < revision2.length ? revision2[i] : "0";
            rStr1 = removeLeadingZero(rStr1);
            rStr2 = removeLeadingZero(rStr2);
            Integer rInt1 = Integer.valueOf(rStr1);
            Integer rInt2 = Integer.valueOf(rStr2);
            if(rInt1 > rInt2)
                return 1;
            if(rInt1 < rInt2)
                return -1;
        }

        return 0;
    }

    private String removeLeadingZero(String revision) {
        int i = 0;
        while(i < revision.length() && revision.charAt(i) == '0') {
            i++;
        }

        if(i == revision.length())
            return "0";

        return revision.substring(i, revision.length());
    }
}
