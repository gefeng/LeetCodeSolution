package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Prime Palindrome",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/prime-palindrome/"
)
public class Q866 {
    /*
    *  不用考虑偶数长度的palindrome number，都可以被11除
    * */
    public int primePalindrome(int N) {
        if(N > 7 && N < 12)
            return 11;
        int ans = 0;
        int seed = 1;
        while(true) {
            String s = Integer.toString(seed);
            String r = new StringBuilder(s).reverse().toString();
            //int evenp = Integer.parseInt(s + r);
            int oddp = Integer.parseInt(s + r.substring(1));
            if(oddp >= N && isPrime(oddp)) {
                ans = oddp;
                break;
            }

            seed++;
        }
        return ans;
    }

    private boolean isPrime(int n) {
        if(n < 2)
            return false;
        for(int i = 2; i * i <= n; i++) {
            if(n % i == 0)
                return false;
        }
        return true;
    }
}
