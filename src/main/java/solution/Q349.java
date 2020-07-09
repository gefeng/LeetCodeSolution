package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashSet;

@Problem(
        title = "Intersections of Two Arrays",
        difficulty = QDifficulty.EASY,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/intersection-of-two-arrays/"
)
public class Q349 {
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        int[] intersection;
        for(int n : nums1)
            set1.add(n);

        for(int n : nums2) {
            if(set1.contains(n))
                set2.add(n);
        }

        int i = 0;
        intersection = new int[set2.size()];
        for(int n : set2)
            intersection[i++] = n;

        return intersection;
    }
}
