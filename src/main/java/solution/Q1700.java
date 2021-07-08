package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of Students Unable to Eat Lunch",
        difficulty = QDifficulty.EASY,
        tag = QTag.QUEUE,
        url = "https://leetcode.com/problems/number-of-students-unable-to-eat-lunch/"
)
public class Q1700 {
    public int countStudents(int[] students, int[] sandwiches) {
        int cntZero = 0;
        int cntOne = 0;
        for(int st : students) {
            if(st == 1) {
                cntOne++;
            } else {
                cntZero++;
            }
        }

        for(int sd : sandwiches) {
            if((sd == 0 && cntZero == 0) || (sd == 1 && cntOne == 0)) {
                break;
            }
            if(sd == 0) {
                cntZero--;
            } else {
                cntOne--;
            }
        }

        return cntOne + cntZero;
    }
}
