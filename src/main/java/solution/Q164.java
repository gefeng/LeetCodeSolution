package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Gap",
        difficulty = QDifficulty.HARD,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/maximum-gap/"
)
public class Q164 {
    private class Bucket {
        int size;
        int max;
        int min;
        Bucket(int size) {
            this.size = size;
            this.max = Integer.MIN_VALUE;
            this.min = Integer.MAX_VALUE;
        }
    }

    public int maximumGap(int[] nums) {
        int n = nums.length;
        if(n == 1) {
            return 0;
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for(int num : nums) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }

        if(max == min) {
            return 0;
        }

        int gap = (max - min + n - 1 - 1) / (n - 1); // minimum maximum gap
        int total = (max - min + 1 + gap - 1) / gap;

        Bucket[] b = new Bucket[total];
        for(int i = 0; i < total; i++) {
            b[i] = new Bucket(gap);
        }

        for(int num : nums) {
            int idx = (num - min) / gap;
            b[idx].min = Math.min(b[idx].min, num);
            b[idx].max = Math.max(b[idx].max, num);
        }

        int maxGap = gap;
        int prevMax = b[0].max;
        for(int i = 1; i < total; i++) {
            if(b[i].min != Integer.MAX_VALUE) {
                maxGap = Math.max(maxGap, b[i].min - prevMax);
                prevMax = b[i].max;
            }
        }
        return maxGap;
    }
}
