package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Valid IP Address",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/validate-ip-address/"
)
public class Q468 {
    /*
    * 体力活，注意一种情况 192.168.0.1.
    * 这种string用split("\\.")也会分成4个子串，但不是valid
    * */
    public String validIPAddress(String IP) {
        if(isValidIPv4(IP)) {
            return "IPv4";
        }

        if(isValidIPv6(IP)) {
            return "IPv6";
        }

        return "Neither";
    }

    private boolean isValidIPv4(String IP) {
        if(IP.lastIndexOf(".") == IP.length() - 1) {
            return false;
        }

        String[] ip = IP.split("\\.");
        if(ip.length != 4) {
            return false;
        }

        for(String portion : ip) {
            int len = portion.length();
            if(len == 0 || len > 3) {
                return false;
            }

            if(len > 1 && portion.indexOf("0") == 0) {
                return false;
            }

            try {
                int n = Integer.parseInt(portion);
                if(n < 0 || n > 255) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidIPv6(String IP) {
        if(IP.lastIndexOf(":") == IP.length() - 1) {
            return false;
        }

        String[] ip = IP.split("\\:");
        if(ip.length != 8) {
            return false;
        }

        for(String portion : ip) {
            int len = portion.length();
            if(len < 1 || len > 4) {
                return false;
            }

            for(int i = 0; i < len; i++) {
                char c = portion.charAt(i);

                if(Character.isDigit(c)) {
                    continue;
                }

                if(Character.isLetter(c) && ((c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F'))) {
                    continue;
                }

                return false;
            }
        }

        return true;
    }
}
