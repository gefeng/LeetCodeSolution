package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Stack;

@Problem(
        title = "Maximum Frequency Stack",
        difficulty = QDifficulty.HARD,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/maximum-frequency-stack/"
)
public class Q895 {
    HashMap<Integer, Integer> mapFreq;
    HashMap<Integer, Stack<Integer>> mapStack;
    int maxFreq;
    public Q895() {
        maxFreq = 0;
        mapFreq = new HashMap<>();
        mapStack = new HashMap<>();
    }

    public void push(int val) {
        int freq = mapFreq.getOrDefault(val, 0) + 1;
        mapFreq.put(val, freq);
        if(freq > maxFreq)
            maxFreq = freq;

        if(!mapStack.containsKey(freq))
            mapStack.put(freq, new Stack<>());
        mapStack.get(freq).push(val);
    }

    public int pop() {
        Stack<Integer> stack = mapStack.get(maxFreq);
        int value = stack.pop();
        int freq = mapFreq.get(value) - 1;
        if(stack.isEmpty()) {
            mapStack.remove(maxFreq);
            maxFreq--;
        }

        if(freq == 0)
            mapFreq.remove(value);
        else
            mapFreq.put(value, freq);
        return value;
    }
}
