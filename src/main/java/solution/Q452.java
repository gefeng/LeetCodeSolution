package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Minimum Number of Arrows to Burst Balloons",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/"
)
public class Q452 {
    /*
    * 区间问题，这题值得总结一下，
    * 这题翻译过来其实意思是要找最大不重叠子区间个数，这类型的问题按照end排列
    *   -----       A
    *    -----      B
    * ----------    C
    *       ----    D
    * 可以看出来凡是end > A.end && start <= A.end的区间都可以一箭射穿
    * 这题的一般形态的思路为如果要想尽可能多的找到不重叠的区间，就要在几个重叠的区间里选一个结束最早的
    * 比如参加几个活动，如果想尽可能多的参加活动，那肯定需要从几个重叠的活动中选一个结束早的。
    * */
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));

        int countArrow = 1;
        int end = points[0][1];
        for(int i = 1; i < points.length; i++) {
            if(points[i][0] > end) {
                countArrow++;
                end = points[i][1];
            }
        }

        return countArrow;
    }
}
