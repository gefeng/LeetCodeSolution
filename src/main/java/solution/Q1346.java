package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Check If N and Its Double Exist",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/check-if-n-and-its-double-exist/"
)
public class Q1346 {
    public boolean checkIfExist(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < arr.length; i++) {
            if(set.contains(arr[i] * 2) || (arr[i] % 2 == 0 && set.contains(arr[i] / 2)))
                return true;

            set.add(arr[i]);
        }
        return false;
    }
}
