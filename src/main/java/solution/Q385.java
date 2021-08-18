package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.List;

@Problem(
        title = "Mini Parser",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DEPTH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/mini-parser/"
)
public class Q385 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    private class NestedInteger {
        NestedInteger() {}
        boolean isInteger() { return false; }
        Integer getInteger() { return 0; }
        List<NestedInteger> getList() { return null; }
        void add(NestedInteger x) {}
        void setInteger(int x) {}
    }
    public NestedInteger deserialize(String s) {
        NestedInteger ni = new NestedInteger();

        if(s.charAt(0) == '[') {
            parseList(s, 1, ni);
        } else {
            parseInteger(s, 0, ni);
        }

        return ni;
    }

    private int parseList(String s, int start, NestedInteger ni) {
        int n = s.length();
        int i = start;
        while(i < n && s.charAt(i) != ']') {
            if(s.charAt(i) == '[') {
                NestedInteger x = new NestedInteger();

                i = parseList(s, i + 1, x);

                ni.add(x);
            } else if(s.charAt(i) == ',') {
                i++;
            } else {
                NestedInteger x = new NestedInteger();

                i = parseInteger(s, i, x);

                ni.add(x);
            }
        }

        return i + 1;
    }

    private int parseInteger(String s, int start, NestedInteger ni) {
        int n = s.length();

        StringBuilder sb = new StringBuilder();
        int i = start;
        while(i < n && (s.charAt(i) == '-' || Character.isDigit(s.charAt(i)))) {
            sb.append(s.charAt(i++));
        }

        ni.setInteger(Integer.valueOf(sb.toString()));

        return i;
    }
}
