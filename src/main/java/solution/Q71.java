package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;

@Problem(
        title = "Simplify Path",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/simplify-path/"
)
public class Q71 {
    public String simplifyPath(String path) {
        Deque<String> stack = new ArrayDeque<>();

        for(String dir : path.split("/+")) {
            if(dir.isEmpty() || dir.equals("."))
                continue;
            if(!dir.equals(".."))
                stack.push(dir);
            else if(!stack.isEmpty())
                stack.pop();
        }

        StringBuilder pathBuilder = new StringBuilder();
        while(!stack.isEmpty()) {
            String dir = stack.pop();
            for(int i = dir.length() - 1; i >= 0; i--) {
                pathBuilder.append(dir.charAt(i));
            }
            pathBuilder.append("/");
        }

        return pathBuilder.length() == 0 ? "/" : pathBuilder.reverse().toString();
    }
}
