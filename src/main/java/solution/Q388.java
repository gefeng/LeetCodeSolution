package solution;

import java.util.Stack;

public class Q388 {
    /*
    * Be careful with '\t' '\n' etc their length count as 1
    * */
    public int lengthLongestPath(String input) {
        int maxLen = 0;
        String[] relativePath = input.split("\\n");
        Stack<int[]> stack = new Stack<>();

        int currLen = 0;
        for(String path : relativePath) {
            int level = getLevel(path);
            int len = getLength(path) + 1;
            boolean isFile = isFile(path);
            while(!stack.isEmpty() && level <= stack.peek()[0])
                currLen -= stack.pop()[1];

            stack.push(new int[] {level, len});
            currLen += len;

            if(isFile)
                maxLen = Math.max(maxLen, currLen - 1);
        }

        return maxLen;
    }

    private int getLevel(String s) {
        int level = 0;
        int i = 0;
        while(i < s.length() && s.charAt(i) == '\t') {
            level++;
            i++;
        }
        return level;
    }

    private int getLength(String s) {
        return s.replaceAll("\\t+", "").length();
    }

    private boolean isFile(String s) {
        return s.contains(".");
    }
}
