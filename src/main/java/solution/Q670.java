package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Maximum Swap",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/maximum-swap/"
)
public class Q670 {
    /*
        for each element search the largest element behind which is larger than current one,
        if exists swap with the last one we found

        如果给定一个较小的range，考虑用counting sort 或者buckets
    */
    public int maximumSwap(int num) {
        char[] numChar = String.valueOf(num).toCharArray();
        int[] buckets = new int[10];
        Arrays.fill(buckets, -1);

        for(int i = 0; i < numChar.length; i++) {
            buckets[numChar[i] - '0'] = i;
        }

        for(int i = 0; i < numChar.length; i++) {
            for(int j = 9; j > numChar[i] - '0'; j--) {
                if(buckets[j] > i) {
                    char temp = numChar[i];
                    numChar[i] = numChar[buckets[j]];
                    numChar[buckets[j]] = temp;
                    return Integer.valueOf(new String(numChar));
                }
            }
        }

        return num;
    }
}
