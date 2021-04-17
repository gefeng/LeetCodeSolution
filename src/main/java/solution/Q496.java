package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Next Greater Element I",
        difficulty = QDifficulty.EASY,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/next-greater-element-i/"
)
public class Q496 {
    /*
    * 单调栈 monotonic stack 思路是用stack来保证stack里的元素ordered
    * */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> idxMap = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();
        int[] nextGreater = new int[nums2.length];

        Arrays.fill(nextGreater, -1);

        for(int i = 0; i < nums2.length; i++) {
            int num = nums2[i];
            idxMap.put(num, i);

            while(!stack.isEmpty() && num > nums2[stack.peek()]) {
                nextGreater[stack.pop()] = num;
            }

            stack.push(i);
        }

        int[] ans = new int[nums1.length];
        for(int i = 0; i < nums1.length; i++) {
            ans[i] = nextGreater[idxMap.get(nums1[i])];
        }
        return ans;
    }
}
