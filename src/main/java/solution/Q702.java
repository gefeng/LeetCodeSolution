package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Search in a Sorted Array of Unknown Size",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/search-in-a-sorted-array-of-unknown-size/"
)
public class Q702 {
    interface ArrayReader {
        int get(int index);
    }
    public int search(ArrayReader reader, int target) {
        int left = 0;
        int right = 1;
        int mid = 0;
        while(reader.get(right) < target) {
            left = right;
            right *= 2;
        }

        while(left <= right) {
            mid = left + (right - left) / 2;
            if(reader.get(mid) == target)
                return mid;
            else if(reader.get(mid) < target)
                left = mid + 1;
            else
                right = mid - 1;
        }

        return -1;
    }
} 
