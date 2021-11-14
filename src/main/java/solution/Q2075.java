package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Decode the Slanted Ciphertext",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/decode-the-slanted-ciphertext/"
)
public class Q2075 {
    /**
     * Time:  O(M * N)
     * Space: O(M * N)
     * */
    public String decodeCiphertext(String encodedText, int rows) {
        int n = encodedText.length();
        int cols = n / rows;

        StringBuilder sb = new StringBuilder();

        for(int c = 0; c < cols; c++) {
            for(int r = 0, d = 0; r < rows && c + d < cols; r++, d++) {
                sb.append(encodedText.charAt(r * cols + c + d));
            }
        }

        return sb.toString().stripTrailing();
    }
}
