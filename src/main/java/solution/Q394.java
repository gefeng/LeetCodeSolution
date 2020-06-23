package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Stack;

@Problem(
        title = "Decode String",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/decode-string/"
)
public class Q394 {
    /**time complexity is O(N) note N is the length of decoded string**/
    public String decodeString(String s) {
        Stack<String> strStack = new Stack<>();
        Stack<Integer> kStack = new Stack<>();
        StringBuilder curr = new StringBuilder();
        int k = 0;

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                k = k * 10 + c - '0';
            } else if(c == '[') {
                kStack.push(k);
                k = 0;
                strStack.push(curr.toString());
                curr = new StringBuilder();
            } else if(c == ']') {
                StringBuilder temp = new StringBuilder(strStack.pop());
                for(int j = kStack.pop(); j > 0; j--)
                    temp.append(curr);
                curr = temp;
            } else {
                curr.append(c);
            }
        }
        return curr.toString();
    }
}
