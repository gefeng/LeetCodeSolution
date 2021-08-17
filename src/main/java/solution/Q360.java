package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Problem(
        title = "Sort Transformed Array",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/sort-transformed-array/"
)
public class Q360 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int n = nums.length;
        int[] res = new int[n];

        if(a == 0 && b == 0) {
            Arrays.fill(res, c);
            return res;
        }
        if(a == 0) {
            if(b < 0) {
                int i = n - 1;
                for(int num : nums) {
                    res[i--] = f(a, b, c, num);
                }
            } else {
                int i = 0;
                for(int num : nums) {
                    res[i++] = f(a, b, c, num);
                }
            }
            return res;
        }

        List<Integer> l = new ArrayList<>();
        List<Integer> r = new ArrayList<>();

        double mid = -b / (2 * a);

        for(int num : nums) {
            if(num < mid) {
                l.add(f(a, b, c, num));
            } else {
                r.add(f(a, b, c, num));
            }
        }

        if(a < 0) {
            int i = 0, j = r.size() - 1, k = 0;
            while(i < l.size() || j >= 0) {
                if(i >= l.size() || (j >= 0 && l.get(i) > r.get(j))) {
                    res[k++] = r.get(j--);
                } else {
                    res[k++] = l.get(i++);
                }
            }
        } else {
            int i = l.size() - 1, j = 0, k = 0;
            while(i >= 0 || j < r.size()) {
                if(i < 0 || (j < r.size() && l.get(i) > r.get(j))) {
                    res[k++] = r.get(j++);
                } else {
                    res[k++] = l.get(i--);
                }
            }
        }

        return res;
    }

    private int f(int a, int b, int c, int x) {
        return a * x * x + b * x + c;
    }
}
