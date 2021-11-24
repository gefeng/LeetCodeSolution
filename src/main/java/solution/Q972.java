package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Equal Rational Numbers",
        difficulty = QDifficulty.HARD,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/equal-rational-numbers/"
)
public class Q972 {
    /**
     * This is a problem to test edge case handling ability...
     *
     * Time:  O(1)
     * Space: O(1)
     * */
    private int MAX = 8;
    public boolean isRationalEqual(String s, String t) {
        String[] arr1 = split(s);
        String[] arr2 = split(t);

        // start with comparing repeating part
        boolean rep1 = !arr1[3].isEmpty();
        boolean rep2 = !arr2[3].isEmpty();
        int int1 = Integer.parseInt(arr1[0]);
        int int2 = Integer.parseInt(arr2[0]);

        if(rep1 && rep2) {
            String fra1 = buildFraction(arr1[2], arr1[3]);
            String fra2 = buildFraction(arr2[2], arr2[3]);
            String cFra1 = compress(fra1);
            String cFra2 = compress(fra2);

            if(cFra1.equals("1.")) {
                int1++;
                cFra1 = "";
            }
            if(cFra2.equals("1.")) {
                int2++;
                cFra2 = "";
            }

            if(cFra1.equals(cFra2)) {
                return int1 == int2;
            } else {
                return false;
            }

        } else if(!rep1 && !rep2){
            String fra1 = trim(buildFraction(arr1[2], arr1[3]));
            String fra2 = trim(buildFraction(arr2[2], arr2[3]));

            if(fra1.equals(fra2)) {
                return int1 == int2;
            } else {
                return false;
            }
        } else {
            String fra1 = buildFraction(arr1[2], arr1[3]);
            String fra2 = buildFraction(arr2[2], arr2[3]);
            if(rep1) {
                String cFra1 = compress(fra1);
                String cFra2 = trim(fra2);
                if(cFra1.equals(fra1)) {
                    return false;
                } else {
                    if(cFra1.equals("1.")) {
                        int1++;
                        cFra1 = "";
                    }
                    return cFra1.equals(cFra2) && int1 == int2;
                }
            } else {
                String cFra1 = trim(fra1);
                String cFra2 = compress(fra2);
                if(cFra2.equals(fra2)) {
                    return false;
                } else {
                    if(cFra2.equals("1.")) {
                        int2++;
                        cFra2 = "";
                    }
                    return cFra1.equals(cFra2) && int1 == int2;
                }
            }
        }
    }

    private String[] split(String s) {
        int n = s.length();
        String[] arr = new String[4];
        Arrays.fill(arr, "");

        StringBuilder sb = new StringBuilder();
        int i = 0;
        while(i < n && s.charAt(i) != '.') {
            sb.append(s.charAt(i++));
        }

        arr[0] = sb.toString();

        if(i < n) {
            arr[1] = ".";
            i++;
        }

        sb = new StringBuilder();
        while(i < n && s.charAt(i) != '(') {
            sb.append(s.charAt(i++));
        }

        arr[2] = sb.toString();
        i++;

        sb = new StringBuilder();
        while(i < n && s.charAt(i) != ')') {
            sb.append(s.charAt(i++));
        }

        arr[3] = sb.toString();

        return arr;
    }

    private String buildFraction(String rep, String pat) {
        StringBuilder sb = new StringBuilder();
        sb.append(rep);

        if(pat.isEmpty()) {
            return sb.toString();
        }

        for(int i = 0; sb.length() < MAX; i = (i + 1) % pat.length()) {
            sb.append(pat.charAt(i));
        }

        return sb.toString();
    }

    private String trim(String fra) {
        int n = fra.length();
        int i = n - 1;

        while(i >= 0 && fra.charAt(i) == '0') {
            i--;
        }

        return fra.substring(0, i + 1);
    }

    private String compress(String fra) {
        int i = fra.length() - 1;

        if(fra.charAt(i) == '0') {
            while(i >= 0 && fra.charAt(i) == '0') {
                i--;
            }

            return fra.substring(0, i + 1);
        } else if(fra.charAt(i) == '9'){
            while(i >= 0 && fra.charAt(i) == '9') {
                i--;
            }

            return i < 0 ? "1." : fra.substring(0, i) + (char)(fra.charAt(i) + 1);
        } else {
            return fra;
        }
    }
}
