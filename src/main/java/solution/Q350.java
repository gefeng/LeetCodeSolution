package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.HashMap;

@Problem(
        title = "Intersection of Two Arrays II",
        difficulty = QDifficulty.EASY,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/intersection-of-two-arrays-ii/"
)
public class Q350 {
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int n : nums1)
            map.put(n, map.getOrDefault(n, 0) + 1);

        int size = 0;
        for(int n : nums2) {
            int count = map.getOrDefault(n, 0);
            if(count > 0) {
                nums1[size++] = n;
                map.put(n, count - 1);
            }
        }

        return Arrays.copyOfRange(nums1, 0, size);
    }

    /**Follow Up
     * 1. What if the given array is already sorted? How would you optimize your algorithm? **/
    public int[] intersectSort(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        if(nums1.length > nums2.length)
            return intersectSort(nums2, nums1);

        int i = 0;
        int j = 0;
        int size = 0;
        while(i < nums1.length && j < nums2.length) {
            if(nums1[i] == nums2[j]) {
                nums1[size++] = nums1[i];
                i++;
                j++;
            }
            else if(nums1[i] < nums2[j])
                i++;
            else
                j++;
        }


        return Arrays.copyOfRange(nums1, 0, size);
    }
}
