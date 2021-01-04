package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;

@Problem(
        title = "Find Right Interval",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/find-right-interval/"
)
public class Q436 {
    public int[] findRightIntervalBinarySearch(int[][] intervals) {
        int[] rightIntervals = new int[intervals.length];
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < intervals.length; i++) {
            map.put(intervals[i][0], i);
        }

        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));

        for(int i = 0; i < intervals.length; i++) {
            int index = binarySearch(intervals, intervals[i][1]);
            int originalIndex = index == -1 ? -1 : map.get(intervals[index][0]);
            rightIntervals[map.get(intervals[i][0])] = originalIndex;
        }

        return rightIntervals;
    }

    private int binarySearch(int[][] intervals, int right) {
        int lo = 0;
        int hi = intervals.length - 1;
        int mid = 0;
        while(lo < hi) {
            mid = lo + (hi - lo) / 2;
            if(intervals[mid][0] == right)
                return mid;
            else if(intervals[mid][0] < right)
                lo = mid + 1;
            else
                hi = mid;
        }

        return intervals[hi][0] >= right ? hi : -1;
    }

    public int[] findRightIntervalTreeMap(int[][] intervals) {
        int[] rightIntervals = new int[intervals.length];
        TreeMap<Integer, Integer> startMap = new TreeMap<>();
        for(int i = 0; i < intervals.length; i++) {
            startMap.put(intervals[i][0], i);
        }

        for(int i = 0; i < intervals.length; i++) {
            int right = intervals[i][1];
            Integer key = startMap.ceilingKey(right);
            if(key == null)
                rightIntervals[i] = -1;
            else
                rightIntervals[i] = startMap.get(key);
        }

        return rightIntervals;
    }
}
