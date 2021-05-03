package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "UTF-8 Validation",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BIT_MANIPULATION,
        url = "https://leetcode.com/problems/utf-8-validation/"
)
public class Q393 {
    public boolean validUtf8(int[] data) {
        return bitManipulationSolution(data);
    }

    private boolean bitManipulationSolution(int[] data) {
        int i = 0;
        while(i < data.length) {
            int mask = 1 << 7; // 10000000

            // count number of bytes
            int numBytes = 0;
            while((data[i] & mask) == mask && mask != 0) {
                numBytes++;
                mask >>= 1;
            }

            if(numBytes == 1 || numBytes > 4) {
                return false;
            }

            // read the rest of the bytes
            i++;
            while(numBytes > 1) {
                if(i >= data.length) {
                    return false;
                }
                int mask1 = 1 << 7; // 10000000
                int mask2 = 1 << 6;
                if((data[i] & mask1) != mask1 || (data[i] & mask2) != 0) {
                    return false;
                }
                i++;
                numBytes--;
            }
        }

        return true;
    }

    private boolean naiveSolution(int[] data) {
        int i = 0;
        while(i < data.length) {
            int[] leadByte = getLeastEightBits(data[i]);

            // count number of bytes
            int numBytes = 0;
            for(int j = 0; j < leadByte.length; j++) {
                if(leadByte[j] == 0) {
                    break;
                }
                numBytes++;
            }

            if(numBytes == 1 || numBytes > 4) {
                return false;
            }

            // read the rest of the bytes
            i++;
            while(numBytes > 1) {
                if(i >= data.length) {
                    return false;
                }
                int[] bits = getLeastEightBits(data[i]);
                if(bits[0] != 1 || bits[1] != 0) {
                    return false;
                }
                i++;
                numBytes--;
            }
        }

        return true;
    }

    private int[] getLeastEightBits(int num) {
        int[] bits = new int[8];
        int i = 7;
        while(num != 0 && i >= 0) {
            bits[i--] = num % 2;
            num >>= 1;
        }

        return bits;
    }
}
