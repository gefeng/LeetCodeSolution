package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Stack;

@Problem(
        title = "Find Permutation",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/find-permutation/"
)
public class Q484 {
    public int[] findPermutationTwoPointer(String s) {
        int n = s.length() + 1;
        int[] ans = new int[n];
        for(int i = 0, j = -1; i < n; i++) {
            if(i == n - 1 || s.charAt(i) == 'I') {
                int val = i + 1;
                for(int k = j + 1; k <= i; k++)
                    ans[k] = val--;
                j = i;
            }
        }

        return ans;
    }

    public int[] findPermutationStack(String s) {
        int n = s.length() + 1;
        int[] ans = new int[n];
        int index = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i = 1; i < n; i++) {
            char c = s.charAt(i - 1);
            stack.push(i);
            while(c == 'I' && !stack.isEmpty()) {
                ans[index++] = stack.pop();
            }
        }

        stack.push(n);
        while(!stack.isEmpty())
            ans[index++] = stack.pop();

        return ans;
    }
}
