package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem (
        title = "Robot Room Cleaner",
        difficulty = QDifficulty.HARD,
        tag = QTag.DEPTH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/robot-room-cleaner/"
)
public class Q489 {
    interface Robot {
        // Returns true if the cell in front is open and robot moves into the cell.
        // Returns false if the cell in front is blocked and robot stays in the current cell.
        boolean move();

        // Robot will stay in the same cell after calling turnLeft/turnRight.
        // Each turn will be 90 degrees.
        // public void turnLeft();
        void turnRight();

        // Clean the current cell.
        void clean();
    }

    private int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    Set<String> visited = new HashSet<>();

    public void cleanRoom(Robot robot) {
        backTrack(robot, 0, 0, 0);
    }

    private void backTrack(Robot robot, int row, int col, int d) {
        robot.clean();
        visited.add(row + "-" + col);

        for(int i = 0; i < 4; i++) {
            int newDir = (d + i) % 4;
            int newRow = row + directions[newDir][0];
            int newCol = col + directions[newDir][1];
            if(!visited.contains(newRow + "-" + newCol) && robot.move()) {
                backTrack(robot, newRow, newCol, newDir);
                goBack(robot);
            }
            robot.turnRight();
        }
    }

    private void goBack(Robot robot) {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }
}
