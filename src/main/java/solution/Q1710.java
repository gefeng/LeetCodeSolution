package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Maximum Units on a Truck",
        difficulty = QDifficulty.EASY,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/maximum-units-on-a-truck/"
)
public class Q1710 {
    /*
    * Amazon OA
    * */
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (a, b) -> (b[1] - a[1]));

        int maxUnits = 0;
        for(int[] type : boxTypes) {
            maxUnits += (type[1] * Math.min(type[0], truckSize));
            truckSize -= type[0];
            if(truckSize <= 0)
                break;
        }

        return maxUnits;
    }
}
