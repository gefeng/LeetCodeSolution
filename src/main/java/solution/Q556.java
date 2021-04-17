package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

@Problem(
        title = "Next Greater Element III",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/next-greater-element-iii/"
)
public class Q556 {
    public int nextGreaterElement(int n) {
        int[] digits = toDigits(n);

        int idx1 = -1;
        int idx2 = -1;
        for(int i = digits.length - 2; i >= 0; i--) {
            if(digits[i] < digits[i + 1]) {
                idx1 = i;
                break;
            }
        }

        if(idx1 == -1)
            return -1;

        for(idx2 = digits.length - 1; idx2 > idx1; idx2--) {
            if(digits[idx2] > digits[idx1])
                break;
        }


        // swap
        swap(digits, idx1, idx2);

        reverse(digits, idx1 + 1, digits.length - 1);

        return toInteger(digits);
    }

    private void reverse(int[] nums, int start, int end) {
        while(start < end) {
            swap(nums, start++, end--);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private int[] toDigits(int n) {
        List<Integer> digits = new ArrayList<>();
        while(n != 0) {
            digits.add(n % 10);
            n /= 10;
        }

        int len = digits.size();
        int[] arr = new int[len];
        for(int i = 0; i < len; i++)
            arr[i] = digits.get(len - 1 - i);
        return arr;
    }

    private int toInteger(int[] digits) {
        long ans = 0;
        for(int i = 0; i < digits.length; i++) {
            ans = ans * 10 + digits[i];
        }

        return ans > Integer.MAX_VALUE ? -1 : (int)ans;
    }
}
