package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Find the Closest Palindrome",
        difficulty = QDifficulty.HARD,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/find-the-closest-palindrome/"
)
public class Q564 {
    /**
     * Time:  O(log(N))
     * Space: O(1)
     * */
    public String nearestPalindromic(String n) {
        long x = Long.parseLong(n);

        if(x == 0) return "1";
        if(x <= 10) return Long.toString(x - 1);

        long lower = findLower(x);
        long upper = findUpper(x);

        long d1 = x - lower;
        long d2 = upper - x;
        if(d1 == d2) return Long.toString(lower);

        return d1 < d2 ? Long.toString(lower) : Long.toString(upper);
    }

    private long findLower(long x) {
        int len = getLength(x);
        long base = (long)Math.pow(10, (len - 1) / 2);
        long lb = base;
        long ub = base * 10 - 1;

        long first = (long)Math.pow(10, len - 1) + 1;
        if(x <= first) return first - 2;

        long l = x / (base * 10);
        long r = reverse(l);
        long pal = len % 2 == 0 ? l * base * 10 + r : x / base * base + r;
        if(pal < x) return pal;

        if(len % 2 == 0) {
            l = l - 1;
            r = reverse(l);
            pal = l * base * 10 + r;
        } else {
            l = x / base - 1;
            r = reverse(l / 10);
            pal = l * base + r;
        }

        return pal;
    }

    private long findUpper(long x) {
        int len = getLength(x);
        long base = (long)Math.pow(10, (len - 1) / 2);
        long lb = base;
        long ub = base * 10 - 1;

        long last = (long)Math.pow(10, len) - 1;
        if(x == last) return last + 2;

        long l = x / (base * 10);
        long r = reverse(l);
        long pal = len % 2 == 0 ? l * base * 10 + r : x / base * base + r;
        if(pal > x) return pal;

        if(len % 2 == 0) {
            l = l + 1;
            r = reverse(l);
            pal = l * base * 10 + r;
        } else {
            l = x / base + 1;
            r = reverse(l / 10);
            pal = l * base + r;
        }

        return pal;
    }

    private int getLength(long x) {
        int len = 0;
        while(x != 0) {
            x /= 10;
            len++;
        }
        return len;
    }

    private long reverse(long x) {
        char[] arr = Long.toString(x).toCharArray();

        for(int l = 0, r = arr.length - 1; l < r; l++, r--) {
            char c = arr[l];
            arr[l] = arr[r];
            arr[r] = c;
        }

        return Long.parseLong(new String(arr));
    }
}
