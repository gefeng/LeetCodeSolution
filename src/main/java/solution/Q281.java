package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;

@Problem(
        title = "Zigzag Iterator",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DESIGN,
        url = "https://leetcode.com/problems/zigzag-iterator/"
)
public class Q281 {
    /*
    * 直接就上queue了，follow up for k lists一起解决了
    * */
    private Queue<ListIterator<Integer>> queue;
    public Q281(List<Integer> v1, List<Integer> v2) {
        queue = new ArrayDeque<>();
        ListIterator<Integer> i1 = v1.listIterator();
        if(i1.hasNext())
            queue.offer(i1);

        ListIterator<Integer> i2 = v2.listIterator();
        if(i2.hasNext())
            queue.offer(i2);
    }

    public int next() {
        ListIterator<Integer> i = queue.poll();
        int val = i.next();
        if(i.hasNext())
            queue.offer(i);

        return val;
    }

    public boolean hasNext() {
        return !queue.isEmpty();
    }
}
