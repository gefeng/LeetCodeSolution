package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Similar RGB Color",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/similar-rgb-color/"
)
public class Q800 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public String similarRGB(String color) {
        int n = color.length();
        int r = Integer.valueOf(color.substring(1, 3), 16);
        int g = Integer.valueOf(color.substring(3, 5), 16);
        int b = Integer.valueOf(color.substring(5, 7), 16);

        int max = Integer.MIN_VALUE;
        String ans = "";
        for(int i = 0; i < 16; i++) {
            for(int j = 0; j < 16; j++) {
                for(int k = 0; k < 16; k++) {
                    int x = i * 16 + i;
                    int y = j * 16 + j;
                    int z = k * 16 + k;
                    int d = -(x - r) * (x - r) - (y - g) * (y - g) - (z - b) * (z - b);

                    if(d > max) {
                        max = d;
                        ans = "#" + toHex(i) + toHex(j) + toHex(k);
                    }
                }
            }
        }

        return ans;
    }

    private String toHex(int x) {
        if(x > 9) {
            char c = (char)('a' + x - 10);
            return "" + c + c;
        } else {
            return "" + Integer.toString(x) + Integer.toString(x);
        }
    }
}
