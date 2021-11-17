package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.Comparator;

@Problem(
        title = "Video Stitching",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/video-stitching/"
)
public class Q1024 {
    public int videoStitching(int[][] clips, int T) {
        Arrays.sort(clips, Comparator.comparingInt(a -> a[0]));

        int count = 0;
        int end = 0;
        int nextEnd = 0;
        int i = 0;

        while(end < T) {

            while(i < clips.length && clips[i][0] <= end) {
                nextEnd = Math.max(nextEnd, clips[i][1]);
                i++;
            }

            if(nextEnd <= end)
                return -1;

            count++;
            end = nextEnd;
        }

        return count;
    }
}
