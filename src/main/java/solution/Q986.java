package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Interval List Intersections",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TWO_POINTERS,
        url = "https://leetcode.com/problems/interval-list-intersections/"
)
public class Q986 {
    /*
        1,2,3
          2,3,4

        |---|               |-----|
          |------|              |---|
        [startj, endi]

          |---|             |-----|
        |-------|            |---|
        [starti, endi]      [startj, endj]

           |---|                |------|
        |-----|               |---|
        [starti, endj]

              |----|
        |---|

        |----|
                |----|
    */
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> intersections = new ArrayList<>();
        int i = 0;
        int j = 0;
        while(i < firstList.length && j < secondList.length) {
            int[] x = firstList[i];
            int[] y = secondList[j];

            if(x[0] >= y[0] && x[0] <= y[1]) {
                if(x[1] > y[1]) {
                    intersections.add(new int[] {x[0], y[1]});
                    j++;
                } else {
                    intersections.add(new int[] {x[0], x[1]});
                    i++;
                }
            } else if(y[0] >= x[0] && y[0] <= x[1]) {
                if(y[1] > x[1]) {
                    intersections.add(new int[] {y[0], x[1]});
                    i++;
                } else {
                    intersections.add(new int[] {y[0], y[1]});
                    j++;
                }
            } else {
                if(x[0] > y[1]) {
                    j++;
                } else if(x[1] < y[0]) {
                    i++;
                }
            }
        }

        return intersections.toArray(new int[0][]);
    }

    /*
        interval [Math.max(starti, startj), Math.min(endi, endj)]
    */
    private int[][] conciseSolution(int[][] firstList, int[][] secondList) {
        List<int[]> intersections = new ArrayList<>();
        int i = 0;
        int j = 0;
        while(i < firstList.length && j < secondList.length) {
            int lo = Math.max(firstList[i][0], secondList[j][0]);
            int hi = Math.min(firstList[i][1], secondList[j][1]);

            if(lo <= hi) {
                intersections.add(new int[] {lo, hi});
            }

            if(firstList[i][1] > secondList[j][1]) {
                j++;
            } else {
                i++;
            }
        }

        return intersections.toArray(new int[0][]);
    }
}
