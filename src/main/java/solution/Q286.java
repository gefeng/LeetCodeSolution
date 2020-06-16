package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.LinkedList;
import java.util.Queue;

@Problem(
        title = "Walls and Gates",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/walls-and-gates/"
)
public class Q286 {
    class Vec2 {
        int r;
        int c;
        Vec2(int r, int c) {this.r = r; this.c = c;}
    }
    private final int EMPTY_ROOM = Integer.MAX_VALUE;
    private final int GATE = 0;
    private final Vec2[] DIRECTION = {
            new Vec2(0, -1),
            new Vec2(0, 1),
            new Vec2(-1, 0),
            new Vec2(1, 0) };

    /**Start from gate instead**/
    public void wallsAndGates(int[][] rooms) {
        Queue<Vec2> queue = new LinkedList<>();
        int height = rooms.length;
        if(height == 0) return;
        int width = rooms[0].length;

        for(int i = 0; i < rooms.length; i++) {
            for(int j = 0; j < rooms[i].length; j++) {
                if(rooms[i][j] == GATE)
                    queue.offer(new Vec2(i, j));
            }
        }

        while(!queue.isEmpty()) {
            Vec2 pos = queue.poll();
            for(Vec2 dir : DIRECTION) {
                Vec2 next = new Vec2(pos.r + dir.r, pos.c + dir.c);
                if(next.r < 0 || next.c < 0 || next.r >= height || next.c >= width || rooms[next.r][next.c] != EMPTY_ROOM)
                    continue;
                rooms[next.r][next.c] = rooms[pos.r][pos.c] + 1;
                queue.add(next);
            }
        }
    }
}
