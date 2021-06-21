package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Car Fleet II",
        difficulty = QDifficulty.HARD,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/car-fleet-ii/"
)
public class Q1776 {
    /*
        if a catches b later than b catches c
        then a catches c instead of b

        if a can't catch b but b catches c
        then test if a catches c

        c->d 4
        b->c 5
        a->b 1
    */
    private class Colide {
        int c;
        double t;
        Colide(int c, double t) {
            this.c = c;
            this.t = t;
        }
    }
    public double[] getCollisionTimes(int[][] cars) {
        int n = cars.length;
        double[] ans = new double[n];
        Deque<Colide> stack = new ArrayDeque<>();

        for(int i = n - 1; i >= 0; i--) {
            while(!stack.isEmpty() && clideAt(cars[i], cars[stack.peek().c]) >= stack.peek().t) {
                Colide top = stack.pop();
                ans[top.c] = top.t == Double.MAX_VALUE ? -1 : top.t;
            }

            if(stack.isEmpty()) {
                stack.push(new Colide(i, Double.MAX_VALUE));
            } else {
                stack.push(new Colide(i, clideAt(cars[i], cars[stack.peek().c])));
            }
        }

        while(!stack.isEmpty()) {
            Colide top = stack.pop();
            ans[top.c] = top.t == Double.MAX_VALUE ? -1 : top.t;
        }

        return ans;
    }

    private double clideAt(int[] c1, int[] c2) {
        if(c1[1] <= c2[1]) {
            return Double.MAX_VALUE;
        }
        return ((double)c2[0] - c1[0]) / (c1[1] - c2[1]);
    }
}
