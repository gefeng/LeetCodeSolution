package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Window Substring",
        difficulty = QDifficulty.HARD,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/minimum-window-substring/"
)
public class Q76 {
    public String minWindow(String s, String t) {
        if(t.length() > s.length())
            return "";
        int[] sBitmap = new int[52];
        int[] tBitmap = new int[52];
        for(int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            tBitmap[getIdx(c)]++;
        }

        int l = 0;
        int r = 0;
        int[] minWindow = new int[2];
        int minSize = s.length();
        for(; r < s.length(); r++) {
            char c = s.charAt(r);
            sBitmap[getIdx(c)]++;
            while(containsAll(sBitmap, tBitmap)) {
                if(r - l < minSize) {
                    minSize = r - l;
                    minWindow[0] = l;
                    minWindow[1] = r;
                }
                sBitmap[getIdx(s.charAt(l++))]--;
            }
        }

        if(minSize == s.length())
            return "";
        return s.substring(minWindow[0], minWindow[1] + 1);
    }

    private int getIdx(char c) {
        return (c >= 'a' && c <= 'z') ? c - 'a' : c - 'A' + 26;
    }

    private boolean containsAll(int[] sBitmap, int[] tBitmap) {
        for(int i = 0; i < sBitmap.length; i++) {
            if(sBitmap[i] < tBitmap[i])
                return false;
        }
        return true;
    }
}
