package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Reconstruct Original Digits from English",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/reconstruct-original-digits-from-english/"
)
public class Q423 {
    /**
     * Follow a specific order to remove letters depending on
     * if the numbers english representation has unique character
     * in the rest of the letters pool.
     *
     * Time:  O(N)
     * Space: O(N)
     * */
    public String originalDigits(String s) {
        int n = s.length();
        int[] cntL = new int[26];
        int[] cntD = new int[10];
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < n; i++) {
            cntL[s.charAt(i) - 'a']++;
        }

        extract(cntL, cntD, "zero", 'z', '0');
        extract(cntL, cntD, "two", 'w', '2');
        extract(cntL, cntD, "eight", 'g', '8');
        extract(cntL, cntD, "three", 'h', '3');
        extract(cntL, cntD, "four", 'r', '4');
        extract(cntL, cntD, "five", 'f', '5');
        extract(cntL, cntD, "seven", 'v', '7');
        extract(cntL, cntD, "six", 's', '6');
        extract(cntL, cntD, "nine", 'i', '9');
        extract(cntL, cntD, "one", 'o', '1');

        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < cntD[i]; j++) {
                sb.append((char)(i + '0'));
            }
        }
        return sb.toString();
    }

    private void extract(int[] cntL, int[] cntD, String num, char unique, char digit) {
        int total = cntL[unique - 'a'];

        if(total == 0) {
            return;
        }

        for(int i = 0; i < num.length(); i++) {
            cntL[num.charAt(i) - 'a'] -= total;
        }

        cntD[digit - '0'] = total;
    }
}
