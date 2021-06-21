package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Equals Sum Arrays With Minimum Number of Operations",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/equal-sum-arrays-with-minimum-number-of-operations/"
)
public class Q1775 {
    public int minOperations(int[] nums1, int[] nums2) {
        int sum1 = 0;
        int sum2 = 0;
        int[] cnt1 = new int[7];
        int[] cnt2 = new int[7];
        for(int n : nums1) {
            sum1 += n;
            cnt1[n]++;
        }
        for(int n : nums2) {
            sum2 += n;
            cnt2[n]++;
        }

        if(sum1 == sum2) {
            return 0;
        }

        int diff = Math.abs(sum1 - sum2);
        if(sum1 < sum2) {
            int[] temp = cnt1;
            cnt1 = cnt2;
            cnt2 = temp;
        }

        int i = 6;
        int j = 1;
        int cnt = 0;
        while(diff > 0) {
            while(i > 1 && cnt1[i] == 0) {
                i--;
            }
            while(j < 6 && cnt2[j] == 0) {
                j++;
            }

            if(i < 2 && j > 5) {
                break;
            }

            if(i < 2) {
                diff -= (6 - j);
                cnt2[j]--;
            } else if(j > 5) {
                diff -= (i - 1);
                cnt1[i]--;
            } else {
                if(i - 1 > 6 - j) {
                    diff -= (i - 1);
                    cnt1[i]--;
                } else {
                    diff -= (6 - j);
                    cnt2[j]--;
                }
            }
            cnt++;
        }

        return diff > 0 ? -1 : cnt;
    }
}
