package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.PriorityQueue;
import java.util.Queue;

@Problem(
        title = "Maximum Average Pass Ratio",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HEAP,
        url = "https://leetcode.com/problems/maximum-average-pass-ratio/"
)
public class Q1792 {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        int n = classes.length;
        Queue<double[]> maxHeap = new PriorityQueue<>((a, b) -> {
            double diff1 = (a[0] + 1) / (a[1] + 1) - a[0] / a[1];
            double diff2 = (b[0] + 1) / (b[1] + 1) - b[0] / b[1];
            return Double.compare(diff2, diff1);
        });

        double ratio = 0;
        for(int[] c : classes) {
            if(c[0] == c[1]) {
                ratio += 1;
            } else {
                maxHeap.offer(new double[] {c[0], c[1]});
            }
        }

        if(maxHeap.isEmpty()) {
            return ratio / n;
        }

        while(extraStudents > 0) {
            double[] c = maxHeap.poll();
            c[0]++;
            c[1]++;
            maxHeap.offer(c);
            extraStudents--;
        }

        while(!maxHeap.isEmpty()) {
            double[] c = maxHeap.poll();
            ratio += (c[0] / c[1]);
        }

        return ratio / n;
    }
}
