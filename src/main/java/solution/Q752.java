package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

@Problem(
        title = "Open the Lock",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BREATH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/open-the-lock/"
)
public class Q752 {
    public String[] getNextValue(String code) {
        String[] values = new String[8];
        for(int i = 0; i < code.length(); i++) {
            char[] next = code.toCharArray();
            char[] prev = code.toCharArray();
            next[i] = (char)((next[i] - '0' + 1) % 10 + '0');
            prev[i] = (char)((prev[i] - '0' - 1 + 10) % 10 + '0');
            values[2 * i] = new String(next);
            values[2 * i + 1] = new String(prev);
        }
        return values;
    }
    public int openLock(String[] deadends, String target) {
        Queue<String> queue = new LinkedList<>();
        HashSet<String> stuck = new HashSet<>();
        HashSet<String> tried = new HashSet<>();
        int turns = 0;
        for(String s : deadends)
            stuck.add(s);

        queue.offer("0000");
        tried.add("0000");
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                String code = queue.poll();
                if(stuck.contains(code))
                    continue;
                if(code.equals(target))
                    return turns;

                String[] values = getNextValue(code);
                for(String v : values) {
                    if(!tried.contains(v)) {
                        queue.offer(v);
                        tried.add(v);
                    }
                }
            }
            turns++;
        }
        return -1;
    }
}
