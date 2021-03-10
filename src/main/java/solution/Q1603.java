package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Design Parking System",
        difficulty = QDifficulty.EASY,
        tag = QTag.DESIGN,
        url = "https://leetcode.com/problems/design-parking-system/"
)
public class Q1603 {
    /*
        slots type: big, medium, small
        big[]
        med[]
        sml[]
        big = 1;
        medium = 2;
        small = 3
    */

    private int[] slotsRemain;
    public Q1603(int big, int medium, int small) {
        slotsRemain = new int[] {big, medium, small};
    }

    public boolean addCar(int carType) {
        if(slotsRemain[carType - 1] == 0)
            return false;
        slotsRemain[carType - 1]--;
        return true;
    }
}
