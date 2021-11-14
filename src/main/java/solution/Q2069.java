package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Walking Robot Simulation II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SIMULATION,
        url = "https://leetcode.com/problems/walking-robot-simulation-ii/"
)
public class Q2069 {
    /**
     * Time:  O(W + H) for move O(1) for 2 gets
     * Space: O(1)
     * */
    private static final int[][] D = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final String[] DS = new String[] {"East", "North", "West", "South"};
    private int d;
    private int x;
    private int y;
    private int w;
    private int h;
    public Q2069(int width, int height) {
        d = 0;
        x = 0;
        y = 0;
        w = width;
        h = height;
    }

    public void move(int num) {
        num = num % (2 * w + (h - 2) * 2);

        if(num == 0) {
            if((x == 0 && y == 0 && d == 0) || (x == w - 1 && y == 0 && d == 1) ||
                    (x == w - 1 && y == h - 1 && d == 2) || (x == 0 && y == h - 1 && d == 3)) {
                d = (d - 1 + 4) % 4;
            }
        } else {
            while(num != 0) {
                int[] dir = D[d];
                int steps = 0;

                if(dir[1] == 0) {
                    if(dir[0] == 1) {
                        steps = Math.min(num, w - x - 1);
                    } else {
                        steps = Math.min(num, x);
                    }
                } else {
                    if(dir[1] == 1) {
                        steps = Math.min(num, h - y - 1);
                    } else {
                        steps = Math.min(num, y);
                    }
                }

                if(steps == 0) {
                    d = (d + 1) % 4;
                    continue;
                }

                num -= steps;

                x = x + steps * dir[0];
                y = y + steps * dir[1];
            }
        }
    }

    public int[] getPos() {
        return new int[] {x, y};
    }

    public String getDir() {
        return DS[d];
    }
}
