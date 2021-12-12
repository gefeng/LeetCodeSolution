package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.PriorityQueue;
import java.util.Queue;

@Problem(
        title = "Sequentially Ordinal Rank Tracker",
        difficulty = QDifficulty.HARD,
        tag = QTag.HEAP,
        url = "https://leetcode.com/problems/sequentially-ordinal-rank-tracker/"
)
public class Q2102 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    class Node {
        String name;
        int score;
        Node(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }
    Queue<Node> minq;
    Queue<Node> maxq;
    int t = 0;
    public Q2102() {
        minq = new PriorityQueue<>((a, b) -> {
            if(a.score == b.score) {
                return b.name.compareTo(a.name);
            }
            return Integer.compare(a.score, b.score);
        });
        maxq = new PriorityQueue<>((a, b) -> {
            if(a.score == b.score) {
                return a.name.compareTo(b.name);
            }
            return Integer.compare(b.score, a.score);
        });
    }

    public void add(String name, int score) {
        maxq.add(new Node(name, score));
    }

    public String get() {
        t++;

        while(minq.size() < t) {
            minq.offer(maxq.poll());
        }
        while(!maxq.isEmpty() && isBetter(maxq.peek(), minq.peek())) {
            Node x = maxq.poll();
            Node y = minq.poll();
            maxq.offer(y);
            minq.offer(x);
        }
        return minq.peek().name;
    }

    private boolean isBetter(Node x, Node y) {
        if(x.score == y.score) {
            return x.name.compareTo(y.name) < 0;
        }
        return x.score > y.score;
    }
}
