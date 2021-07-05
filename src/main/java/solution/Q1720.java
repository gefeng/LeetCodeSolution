package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Decode XORed Array",
        difficulty = QDifficulty.EASY,
        tag = QTag.BIT_MANIPULATION,
        url = "https://leetcode.com/contest/weekly-contest-223/problems/decode-xored-array/"
)
public class Q1720 {
    /*
    * a ^ x = b -> x = a ^ b
    * */
    public int[] decode(int[] encoded, int first) {
        int n = encoded.length + 1;
        int decoded[] = new int[n];

        decoded[0] = first;
        for(int i = 1; i < n; i++) {
            decoded[i] = decoded[i - 1] ^ encoded[i - 1];
        }
        return decoded;
    }
}
