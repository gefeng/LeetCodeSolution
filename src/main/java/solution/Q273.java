package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;

@Problem(
        title = "Integer to English Words",
        difficulty = QDifficulty.HARD,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/integer-to-english-words/"
)
public class Q273 {
    private HashMap<Integer, String> suffixMap = new HashMap<>() {
        {
            put(1000000000, "Billion");
            put(1000000, "Million");
            put(1000, "Thousand");
            put(1, "");
        }
    };
    private HashMap<Integer, String> underTen = new HashMap<>() {
        {
            put(1, "One");
            put(2, "Two");
            put(3, "Three");
            put(4, "Four");
            put(5, "Five");
            put(6, "Six");
            put(7, "Seven");
            put(8, "Eight");
            put(9, "Nine");
        }
    };
    private HashMap<Integer, String> underTwenty = new HashMap<>() {
        {
            put(10, "Ten");
            put(11, "Eleven");
            put(12, "Twelve");
            put(13, "Thirteen");
            put(14, "Fourteen");
            put(15, "Fifteen");
            put(16, "Sixteen");
            put(17, "Seventeen");
            put(18, "Eighteen");
            put(19, "Nineteen");
        }
    };
    private HashMap<Integer, String> underHundred = new HashMap<>() {
        {
            put(20, "Twenty");
            put(30, "Thirty");
            put(40, "Forty");
            put(50, "Fifty");
            put(60, "Sixty");
            put(70, "Seventy");
            put(80, "Eighty");
            put(90, "Ninety");
        }
    };
    public String numberToWords(int num) {
        if(num == 0)
            return "Zero";

        HashMap<Integer, String> suffixMap = new HashMap<>() {
            {
                put(1000000000, "Billion");
                put(1000000, "Million");
                put(1000, "Thousand");
                put(1, "");
            }
        };


        StringBuilder sb = new StringBuilder();

        // num >= 1000
        for(int i = 1000000000; i >= 1; i /= 1000) {
            int prefix = num / i;
            if(prefix != 0) {
                sb.append(buildPrefix(prefix)).append(' ');
                sb.append(suffixMap.get(i)).append(' ');
                num = num - prefix * i;
            }
        }

        return sb.toString().trim();
    }


    private String buildPrefix(int num) {
        StringBuilder sb = new StringBuilder();

        // build hundred
        int prefix = num / 100;
        if(prefix != 0) {
            sb.append(underTen.get(prefix)).append(' ').append("Hundred").append(' ');
            num = num - prefix * 100;
        }

        prefix = num / 20;
        if(prefix != 0) {
            sb.append(underHundred.get(num / 10 * 10)).append(' ');
            num = num - (num / 10 * 10);
        }

        if(num > 9)
            sb.append(underTwenty.get(num));
        else if(num > 0 && num < 10)
            sb.append(underTen.get(num));

        return sb.toString().trim();
    }
}
