package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Rotated Digits",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/rotated-digits/"
)
public class Q788 {
    public int rotatedDigits(int N) {
        int count = 0;
        for(int i = 1; i <= N; i++) {
            int number = i;
            boolean isValid = false;
            while(number > 0){
                int digit = number % 10;
                if(digit == 3 || digit == 4 || digit == 7) {
                    isValid = false;
                    break;
                }
                else if(digit == 2 || digit == 5 || digit == 6 || digit == 9)
                    isValid = true;
                number /= 10;
            }
            if(isValid)
                count++;
        }
        return count;
    }
}
