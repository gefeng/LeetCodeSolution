package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Rearrange String k Distance Apart",
        difficulty = QDifficulty.HARD,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/rearrange-string-k-distance-apart/"
)
public class Q358 {
    public String rearrangeString(String s, int k) {
        int n = s.length();
        int[] freq = new int[26]; // letter's frequency
        int[] prev = new int[26]; // letter's previous position

        for(int i = 0; i < n; i++) {
            freq[s.charAt(i) - 'a']++;
        }
        Arrays.fill(prev, -1);

        StringBuilder sb = new StringBuilder();
        while(sb.length() != n) {
            Character letter = getNextLetter(freq, prev, k, sb.length());
            if(letter == null) {
                return "";
            }
            sb.append(letter);
        }

        return sb.toString();
    }

    private Character getNextLetter(int[] freq, int[] prev, int k, int curr) {
        int idx = -1;
        int maxFreq = 0;
        for(int i = 0; i < freq.length; i++) {
            if(freq[i] > maxFreq) {
                if(prev[i] == -1 || curr - prev[i] >= k) {
                    maxFreq = freq[i];
                    idx = i;
                }
            }
        }

        if(idx == -1) {
            return null;
        }

        prev[idx] = curr;
        freq[idx]--;

        return (char)(idx + 'a');
    }
}
