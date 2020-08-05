package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "The Skyline Problem",
        difficulty = QDifficulty.HARD,
        tag = QTag.DIVIDE_AND_CONQUER,
        url = "https://leetcode.com/problems/the-skyline-problem/"
)
public class Q218 {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        if(buildings.length == 0)
            return new ArrayList<>();
        if(buildings.length == 1) {
            List<Integer> keyPoint = new ArrayList<>();
            List<Integer> terPoint = new ArrayList<>();
            List<List<Integer>> skyline = new ArrayList<>();
            keyPoint.add(buildings[0][0]);
            keyPoint.add(buildings[0][2]);
            terPoint.add(buildings[0][1]);
            terPoint.add(0);
            skyline.add(keyPoint);
            skyline.add(terPoint);
            return skyline;
        }

        int mid = buildings.length / 2;
        List<List<Integer>> left = getSkyline(Arrays.copyOfRange(buildings, 0, mid));
        List<List<Integer>> right = getSkyline(Arrays.copyOfRange(buildings, mid, buildings.length));

        return merge(left, right);
    }

    private List<List<Integer>> merge(List<List<Integer>> left, List<List<Integer>> right) {
        List<List<Integer>> skyline = new ArrayList<>();

        int lHeight = 0;
        int rHeight = 0;
        int x = 0;
        while(!left.isEmpty() || !right.isEmpty()) {
            if(left.isEmpty())
                skyline.add(right.remove(0));
            else if(right.isEmpty())
                skyline.add(left.remove(0));
            else {
                List<Integer> k1 = left.get(0);
                List<Integer> k2 = right.get(0);
                if(k1.get(0) < k2.get(0)) {
                    x = k1.get(0);
                    lHeight = left.remove(0).get(1);
                }
                else if(k1.get(0) > k2.get(0)) {
                    x = k2.get(0);
                    rHeight = right.remove(0).get(1);
                }
                else {
                    x = k1.get(0);
                    lHeight = left.remove(0).get(1);
                    rHeight = right.remove(0).get(1);
                }
                int h = Math.max(lHeight, rHeight);
                if(skyline.isEmpty() || h != skyline.get(skyline.size() - 1).get(1)) {
                    List<Integer> keyPoint = new ArrayList<>();
                    keyPoint.add(x);
                    keyPoint.add(h);
                    skyline.add(keyPoint);
                }
            }
        }

        return skyline;
    }

    /*Use priority queue*/
    public List<List<Integer>> getSkylinePQ(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<>();
        List<List<Integer>> height = new ArrayList<>();
        for(int[] b:buildings) {
            height.add(Arrays.asList(b[0], -b[2]));
            height.add(Arrays.asList(b[1], b[2]));
        }
        Collections.sort(height, (a, b) -> {
            if(!a.get(0).equals(b.get(0)))
                return a.get(0) - b.get(0);
            return a.get(1) - b.get(1);
        });
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        pq.offer(0);
        int prev = 0;
        for(List<Integer> h : height) {
            if(h.get(1) < 0) {
                pq.offer(-h.get(1));
            } else {
                pq.remove(h.get(1));
            }
            int cur = pq.peek();
            if(prev != cur) {
                result.add(Arrays.asList(h.get(0), cur));
                prev = cur;
            }
        }
        return result;
    }
}
