package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Decompress Run-Length Encoded List",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/decompress-run-length-encoded-list/"
)
public class Q1313 {
    public int[] decompressRLElist(int[] nums) {
        int length = 0;
        for(int i = 0; i < nums.length - 1; i+=2) {
            length += nums[i];
        }

        int[] retVal = new int[length];
        for(int i = 0, index = 0; i < nums.length - 1; i+=2) {
            int freq = nums[i];
            int val = nums[i + 1];
            for(int j = index; j < index + freq; ++j) {
                retVal[j] = val;
            }
            index += freq;
        }
        return retVal;
    }
}
