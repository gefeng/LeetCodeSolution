package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Robot Bounded In Circle",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/robot-bounded-in-circle/"
)
public class Q1041 {
    public boolean isRobotBounded(String instructions) {
        int[][] directions = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int dirIndex = 0;
        int[] currPos = new int[] {0, 0};
        for(int i = 0; i < instructions.length(); i++) {
            char c = instructions.charAt(i);
            if(c == 'G') {
                currPos[0] += directions[dirIndex][0];
                currPos[1] += directions[dirIndex][1];
            }
            else if(c == 'L') {
                dirIndex = dirIndex - 1 < 0 ? 3 : dirIndex - 1;
            }
            else {
                dirIndex = dirIndex + 1 > 3 ? 0 : dirIndex + 1;

            }
        }

        return (currPos[0] == 0 && currPos[1] == 0) || dirIndex != 0;
    }
}
