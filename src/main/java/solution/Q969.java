package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Pancake Sorting",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/pancake-sorting/"
)
public class Q969 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    public List<Integer> pancakeSort(int[] A) {
        List<Integer> ans = new ArrayList<>();
        for(int i = A.length - 1; i >= 0; i--) {
            int maxIdx = findMax(A, i + 1);
            flip(A, 0, maxIdx);
            ans.add(maxIdx+1);
            flip(A, 0, i);
            ans.add(i+1);
        }
        return ans;
    }

    private void flip(int[] A, int start, int end) {
        while(start < end) {
            int temp = A[start];
            A[start] = A[end];
            A[end] = temp;
            start++;
            end--;
        }
    }

    private int findMax(int[] A, int target) {
        int idx = 0;
        for(int i = 0; i < A.length; i++) {
            if(A[i] == target) {
                idx = i;
                break;
            }
        }
        return idx;
    }
}
