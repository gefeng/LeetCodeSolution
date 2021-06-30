package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of Wonderful Substrings",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BIT_MANIPULATION,
        url = "https://leetcode.com/problems/number-of-wonderful-substrings/"
)
public class Q1915 {
    /*
    * 类似subarray sum to the target。
    * 用bit mask表示prefix里包含字母的奇偶个数，
    * 比如1001代表当前prefix中有奇数个a，d，偶数个b，c。
    * count所有出现过的state，类似于sum lookup table如果遇到相同的state，
    * 说明s[j + 1, i]是一个字母出现频率都为偶数的substring (even - even or odd - odd = even)
    * 同时还要逐位flip，检查所有奇偶性相差1的substring (even - odd or odd - even = odd)
    * */
    public long wonderfulSubstrings(String word) {
        int n = word.length();
        long cnt = 0;

        long[] cntState = new long[1024];
        cntState[0] = 1;

        for(int i = 0, mask = 0; i < n; i++) {
            char c = word.charAt(i);

            mask ^= (1 << (c - 'a')); // flip the bit

            cnt += cntState[mask];

            for(int j = 0; j < 10; j++) {
                cnt += cntState[mask ^ (1 << j)];
            }

            cntState[mask]++;
        }

        return cnt;
    }
}
