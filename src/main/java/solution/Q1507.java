package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Reformat Date",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/reformat-date/"
)
public class Q1507 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public String reformatDate(String date) {
        String[] s = date.split(" ");
        StringBuilder sb = new StringBuilder();

        sb.append(s[2]).append("-");
        sb.append(getMonth(s[1])).append("-");
        sb.append(getDay(s[0]));

        return sb.toString();
    }

    private String getMonth(String m) {
        String s = "";
        switch(m) {
            case "Jan": s = "01"; break;
            case "Feb": s = "02"; break;
            case "Mar": s = "03"; break;
            case "Apr": s = "04"; break;
            case "May": s = "05"; break;
            case "Jun": s = "06"; break;
            case "Jul": s = "07"; break;
            case "Aug": s = "08"; break;
            case "Sep": s = "09"; break;
            case "Oct": s = "10"; break;
            case "Nov": s = "11"; break;
            case "Dec": s = "12"; break;
        }

        return s;
    }

    private String getDay(String d) {
        int n = d.length();

        String s = d.substring(0, n - 2);
        if(s.length() == 1) {
            return "0" + s;
        }

        return s;
    }
}
