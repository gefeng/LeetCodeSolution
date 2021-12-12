package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Watering Plants II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SIMULATION,
        url = "https://leetcode.com/problems/watering-plants-ii/"
)
public class Q2105 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int minimumRefill(int[] plants, int ca, int cb) {
        int n = plants.length;
        int ans = 0;

        int curA = ca;
        int curB = cb;
        for(int l = 0, r = n - 1; l <= r; l++, r--) {
            if(l == r) {
                if(curA > curB) {
                    ans += curA < plants[l] ? 1 : 0;
                } else if(curA < curB) {
                    ans += curB < plants[l] ? 1 : 0;
                } else {
                    ans += curA < plants[l] ? 1 : 0;
                }
            } else {
                if(curA < plants[l]) {
                    ans++;
                    curA = ca;
                }
                curA -= plants[l];

                if(curB < plants[r]) {
                    ans++;
                    curB = cb;
                }
                curB -= plants[r];
            }

        }

        return ans;
    }
}
