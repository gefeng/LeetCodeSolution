package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Split Array into Fibonacci Sequence",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/split-array-into-fibonacci-sequence/"
)
public class Q842 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public List<Integer> splitIntoFibonacci(String num) {
        List<Integer> ans = new ArrayList<>();
        char[] arr = num.toCharArray();
        int n = arr.length;

        long first = 0;
        for(int i = 0; i < n; i++) {
            if(first == 0 && i > 0) return new ArrayList<>();

            first = first * 10 + arr[i] - '0';
            if(first > Integer.MAX_VALUE) return new ArrayList<>();

            long second = 0;
            for(int j = i + 1; j < n; j++) {
                if(second == 0 && arr[j] == '0') {
                    ans = isFib(first, second, arr, j + 1);
                    if(ans.isEmpty()) {
                        break;
                    }
                    return ans;
                }

                second = second * 10 + arr[j] - '0';

                if(second > Integer.MAX_VALUE) break;

                ans = isFib(first, second, arr, j + 1);
                if(!ans.isEmpty()) return ans;
            }
        }

        return new ArrayList<>();
    }

    private List<Integer> isFib(long x, long y, char[] arr, int st) {
        List<Integer> ans = new ArrayList<>();
        ans.add((int)x);
        ans.add((int)y);

        int n = arr.length;

        if(st == n) return new ArrayList<>();

        long cur = 0;
        for(int i = st; i < n; i++) {
            if(x + y > Integer.MAX_VALUE) return new ArrayList<>();

            cur = cur * 10 + arr[i] - '0';
            if(cur > Integer.MAX_VALUE) return new ArrayList<>();

            if(i == n - 1 && (x + y) != cur) return new ArrayList<>();

            if(cur == x + y) {
                ans.add((int)cur);
                x = y;
                y = cur;
                cur = 0;
            }
        }

        return ans;
    }
}
