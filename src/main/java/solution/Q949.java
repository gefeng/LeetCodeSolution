package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Largest Time for Given Digits",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/largest-time-for-given-digits/"
)
public class Q949 {
    private int maxTime = -1;
    public String largestTimeFromDigits(int[] A) {
        backTrack(A, 0);
        if(maxTime == -1)
            return "";

        int hour = maxTime / 60;
        int minute = maxTime % 60;
        String hourStr = hour < 10 ? "0" + hour : "" + hour;
        String minuteStr = minute < 10 ? "0" + minute : "" + minute;
        return hourStr + ":" + minuteStr;
    }

    private void backTrack(int[] arr, int start) {
        if(start == arr.length) {
            buildTime(arr);
            return;
        }
        for(int i = start; i < arr.length; i++) {
            swap(arr, start, i);
            backTrack(arr, start + 1);
            swap(arr, start, i);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void buildTime(int[] arr) {
        int hour = arr[0] * 10 + arr[1];
        int minute = arr[2] * 10 + arr[3];
        if(hour < 24 && minute < 60) {
            maxTime = Math.max(maxTime, hour * 60 + minute);
        }
    }
}
