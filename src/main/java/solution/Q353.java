package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

@Problem(
        title = "Design Snake Game",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DESIGN,
        url = "https://leetcode.com/problems/design-snake-game/"
)
public class Q353 {
    /*
    * 两个优化点:
    * 1. 蛇的移动的方式：去尾，加一个新头 (不用身体每个节点都移动) O(n) -> O(1)
    * 2. 判断有没有咬到自己可以用hashset保存身体 O(n) -> O(1)
    * */
    private static final HashMap<String, int[]> DIRECTIONS = new HashMap<>() {
        {
            put("U", new int[] { -1, 0 });
            put("D", new int[] { 1, 0 });
            put("R", new int[] { 0, 1 });
            put("L", new int[] { 0, -1 });
        }
    };

    private int width;
    private int height;
    private Queue<int[]> food;
    private LinkedList<int[]> snake;
    private HashSet<Integer> body;
    private int[] dir;
    /** Initialize your data structure here.
     @param width - screen width
     @param height - screen height
     @param food - A list of food positions
     E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public Q353(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;

        this.food = new LinkedList<>();
        for(int[] f : food)
            this.food.offer(f);

        snake = new LinkedList<>();
        snake.add(new int[] {0, 0});

        body = new HashSet<>();
    }

    /** Moves the snake.
     @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     @return The game's score after the move. Return -1 if game over.
     Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        dir = DIRECTIONS.get(direction);

        int[] head = snake.get(0);
        int[] tail = snake.removeLast();
        snake.addFirst(new int[] { head[0] + dir[0], head[1] + dir[1] });
        if(body.size() != 0) {
            body.remove(tail[0] * width + tail[1]);
            body.add(head[0] * width + head[1]);
        }

        if(isGameOver())
            return -1;

        if(!food.isEmpty()) {
            int[] f = food.peek();
            int[] h = snake.get(0);
            if(h[0] == f[0] && h[1] == f[1]) {
                food.poll();
                int[] oldTail = snake.get(snake.size() - 1);
                int[] newTail = new int[] { oldTail[0] - dir[0], oldTail[1] - dir[1] };
                snake.add(newTail);
                body.add(newTail[0] * width + newTail[1]);
            }
        }

        return snake.size() - 1;
    }

    private boolean isGameOver() {
        int[] head = snake.get(0);
        // hit wall
        if(head[0] > height - 1 || head[0] < 0 || head[1] > width - 1 || head[1] < 0)
            return true;

        // bite self
        if(body.contains(head[0] * width + head[1]))
            return true;

        return false;
    }
}
