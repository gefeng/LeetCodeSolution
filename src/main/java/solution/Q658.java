package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Find K Closest Elements",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/find-k-closest-elements/"
)
public class Q658 {
    private int findClosest(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int mid = 0;
        int closest = 0;
        while(left <= right) {
            mid = left + (right - left) / 2;
            if(arr[mid] == target)
                return mid;
            closest = Math.abs(arr[mid] - target) < Math.abs(arr[closest] - target) ? mid : closest;
            if(arr[mid] < target)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return closest;
    }
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> closest = new ArrayList<>();
        if(x <= arr[0]) {
            for(int i = 0; i < k; i++)
                closest.add(arr[i]);
            return closest;
        } else if(x >= arr[arr.length - 1]) {
            for(int i = arr.length - k; i < arr.length; i++) {
                closest.add(arr[i]);
            }
            return closest;
        } else {
            int cIndex = findClosest(arr, x);
            int low = Math.max(cIndex - k + 1, 0);
            int high = Math.min(cIndex + k - 1, arr.length - 1);
            while(high - low > k - 1) {
                if(Math.abs(arr[high] - x) >= Math.abs(arr[low] - x))
                    high--;
                else
                    low++;
            }
            for(int i = low; i < high + 1; i++) {
                closest.add(arr[i]);
            }
            return closest;
        }
    }
}
