package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Problem(
        title = "Guess the Word",
        difficulty = QDifficulty.HARD,
        tag = QTag.INTERACTIVE,
        url = "https://leetcode.com/problems/guess-the-word/"
)
public class Q843 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    private interface Master {
        int guess(String s);
    }
    public void findSecretWord(String[] wordlist, Master master) {
        Random rand = new Random();
        List<String> cand = new ArrayList();

        for(String w : wordlist) cand.add(w);

        for(int i = 0; i < 10; i++) {
            List<String> ncand = new ArrayList<>();

            String t = cand.get(rand.nextInt(cand.size()));

            int res = master.guess(t);

            if(res == 6) {
                return;
            }

            for(String s : cand) {
                if(compare(t, s) == res) {
                    ncand.add(s);
                }
            }

            cand = ncand;
        }
    }

    private int compare(String s1, String s2) {
        int cnt = 0;
        for(int i = 0; i < 6; i++) {
            if(s1.charAt(i) == s2.charAt(i)) {
                cnt++;
            }
        }

        return cnt;
    }
}
