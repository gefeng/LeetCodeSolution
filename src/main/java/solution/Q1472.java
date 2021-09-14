package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Design Browser History",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/design-browser-history/"
)
public class Q1472 {
    private class Node {
        String url;
        Node next;
        Node prev;
        Node(String url) {
            this.url = url;
            this.next = null;
            this.prev = null;
        }
    }

    private Node head;
    private Node curr;

    /**
     * Time:  O(1)
     * */
    public Q1472(String homepage) {
        head = new Node(homepage);
        curr = head;
    }

    /**
     * Time:  O(1)
     * */
    public void visit(String url) {
        Node node = new Node(url);

        curr.next = null;
        curr.next = node;
        node.prev = curr;

        curr = curr.next;
    }

    /**
     * Time:  O(S)
     * */
    public String back(int steps) {
        while(curr != head && steps > 0) {
            curr = curr.prev;
            steps--;
        }

        return curr.url;
    }

    /**
     * Time:  O(S)
     * */
    public String forward(int steps) {
        while(curr.next != null && steps > 0) {
            curr = curr.next;
            steps--;
        }

        return curr.url;
    }
}
