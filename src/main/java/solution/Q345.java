package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;

@Problem(
        title = "Reverse Vowels of a String",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/reverse-vowels-of-a-string/"
)
public class Q345 {
    public String reverseVowels(String s) {
        char ret[] = s.toCharArray();
        int head = 0;
        int tail = s.length() - 1;
        HashSet<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        vowels.add('A');
        vowels.add('E');
        vowels.add('I');
        vowels.add('O');
        vowels.add('U');

        while(head < tail) {
            while (head < tail && !vowels.contains(ret[head]))
                head++;
            while (head < tail && !vowels.contains(ret[tail]))
                tail--;

            char temp = ret[head];
            ret[head] = ret[tail];
            ret[tail] = temp;

            head++;
            tail--;
        }
        return new String(ret);
    }
}
