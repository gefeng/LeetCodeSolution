package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Adjacent Swaps to Reach the Kth Smallest Number",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/minimum-adjacent-swaps-to-reach-the-kth-smallest-number/"
)
public class Q1850 {
    /**
     * Next permutation + brute force adjacent swaps
     *
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    public int getMinSwaps(String num, int k) {
        String kth = num;
        for(int i = 0; i < k; i++) {
            kth = next(kth);
        }

        return minSwaps(num, kth);
    }

    private String next(String num) {
        char[] arr = num.toCharArray();
        int n = num.length();
        int i = n - 2;
        while(i >= 0 && arr[i] >= arr[i + 1]) {
            i--;
        }

        int j = n - 1;
        while(arr[j] <= arr[i]) {
            j--;
        }

        swap(arr, i, j);

        for(int l = i + 1, r = n - 1; l < r; l++, r--) {
            swap(arr, l, r);
        }

        return new String(arr);
    }

    private void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private int minSwaps(String s, String t) {
        int ans = 0;
        int n = s.length();
        char[] arr = s.toCharArray();

        for(int i = 0; i < n; i++) {
            if(arr[i] == t.charAt(i)) continue;

            int j = i;
            while(arr[j] != t.charAt(i)) {
                j++;
            }

            while(arr[i] != t.charAt(i)) {
                swap(arr, j, j - 1);
                j--;
                ans++;
            }
        }

        return ans;
    }
}
