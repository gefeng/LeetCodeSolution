package solution;

import annotations.Problem;
import org.junit.jupiter.api.Test;

import java.sql.PseudoColumnUsage;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestSuite {
    @Test
    public void q937Test() {
        String[] input = new String[] {"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"};
        String[] expected = new String[] {"let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"};
        String[] output = new Q937().reorderLogFiles(input);
        for(int i = 0; i < output.length; i++)
            assertEquals(expected[i], output[i]);
    }

    @Test
    public void q1108Test() {
        String[] input = {"1.1.1.1", "255.100.50.0"};
        String[] expected = {"1[.]1[.]1[.]1", "255[.]100[.]50[.]0"};
        for(int i = 0; i < input.length; i++)
            assertEquals(expected[i], new Q1108().defangIPaddr(input[i]));
    }

    @Test
    public void q1071Test() {
        String input1 = "ABCABC";
        String input2 = "ABC";
        String expected = "ABC";
        assertEquals(expected, new Q1071().gcdOfStrings(input1, input2));
        assertEquals(expected, new Q1071().gcdOfStringsRecursive(input1, input2));
    }

    @Test
    public void q1221Test() {
        String[] input = new String[] {
                "RLRRLLRLRL",
                "RLLLLRRRLR",
                "LLLLRRRR",
                "RLRRRLLRLL"
        };
        int[] expected = new int[] {4, 3, 1, 2};
        for(int i = 0; i < input.length; i++) {
            assertEquals(expected[i], new Q1221().balancedStringSplit(input[i]));
        }
    }

    @Test
    public void q286Test() {
        int[][] rooms = {{2147483647,0,2147483647,2147483647,0,2147483647,-1,2147483647}};
        new Q286().wallsAndGates(rooms);
    }

    
    @Test
    public void q200Test() {
        char[][] grid = {
                {'1','1','1','1','1','0','1','1','1','1','1','1','1','1','1','0','1','0','1','1'},
                {'0','1','1','1','1','1','1','1','1','1','1','1','1','0','1','1','1','1','1','0'},
                {'1','0','1','1','1','0','0','1','1','0','1','1','1','1','1','1','1','1','1','1'},
                {'1','1','1','1','0','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'},
                {'1','0','0','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'},
                {'1','0','1','1','1','1','1','1','0','1','1','1','0','1','1','1','0','1','1','1'},
                {'0','1','1','1','1','1','1','1','1','1','1','1','0','1','1','0','1','1','1','1'},
                {'1','1','1','1','1','1','1','1','1','1','1','1','0','1','1','1','1','0','1','1'},
                {'1','1','1','1','1','1','1','1','1','1','0','1','1','1','1','1','1','1','1','1'},
                {'1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'},
                {'0','1','1','1','1','1','1','1','0','1','1','1','1','1','1','1','1','1','1','1'},
                {'1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'},
                {'1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'},
                {'1','1','1','1','1','0','1','1','1','1','1','1','1','0','1','1','1','1','1','1'},
                {'1','0','1','1','1','1','1','0','1','1','1','0','1','1','1','1','0','1','1','1'},
                {'1','1','1','1','1','1','1','1','1','1','1','1','0','1','1','1','1','1','1','0'},
                {'1','1','1','1','1','1','1','1','1','1','1','1','1','0','1','1','1','1','0','0'},
                {'1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'},
                {'1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'},
                {'1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'}        
        };
        new Q200().numIslands(grid);
    }

    @Test
    public void q752Test() {
        Q752 q = new Q752();
        String[] deadends = {"8887","8889","8878","8898","8788","8988","7888","9888"};
        String target = "8888";
        q.openLock(deadends, target);
    }

    @Test
    public void q279Test() {
        Q279 q = new Q279();
        q.numSquaresBFS(18);
    }
    
    @Test
    public void q739Test() {
        Q739 q = new Q739();
        q.dailyTemperatures(new int[] {73,74,75,71,69,72,76,73});
    }

    @Test
    public void q494Test() {
        Q494 q = new Q494();
        q.findTargetSumWaysMS(new int[] {1, 1, 1, 1, 1}, 3);
    }

    @Test
    public void q498Test() {
        Q498 q = new Q498();
        q.findDiagonalOrder(new int[][] { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} });
    }

    @Test
    public void q54Test() {
        Q54 q = new Q54();
        q.spiralOrder(new int[][] { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} });
    }

    @Test
    public void q151Test() {
        Q151 q = new Q151();
        System.out.println(q.reverseWordsBuildin("this is  a   string!  "));
    }

    @Test
    public void q69Test() {
        Q69 q = new Q69();
        q.mySqrt(2147395599);
    }

    @Test
    public void q658Test() {
        Q658 q = new Q658();
        q.findClosestElements(new int[] {0,2,2,3,4,6,7,8,9,9}, 4, 5);
    }

    @Test
    public void q367Test() {
        Q367 q = new Q367();
        q.isPerfectSquare(808201);
    }

    @Test
    public void q719Test() {
        Q719 q = new Q719();
        q.smallestDistancePair(new int[] {9,10,7,10,6,1,5,4,9,8}, 18);
    }

    @Test
    public void q136Test() {
        Q136 q = new Q136();
        q.singleNumber(new int[] {2, 2, 1});
    }

    @Test
    public void q202Test() {
        Q202 q = new Q202();
        q.isHappy(2);
    }

    @Test
    public void q381Test() {
        Q381 q = new Q381();
        q.insert(4);
        q.insert(3);
        q.insert(4);
        q.insert(2);
        q.insert(4);
        q.remove(4);
        q.remove(3);
        q.remove(4);
        q.remove(4);
        q.getRandom();
    }

    @Test
    public void q912Test() {
        Q912 q = new Q912();
        q.sortArray(new int[] {5, 1, 2, 3});
    }

    @Test
    public void q52Test() {
        Q52 q = new Q52();
        q.totalNQueens(4);
        q.placeQueen(2, 1, 4);
        q.removeQueen(2, 1, 4);
        q.placeQueen(1, 2, 4);
    }

    @Test
    public void q77Test() {
        Q77 q = new Q77();
        q.combine(4, 2);
    }

    @Test
    public void q84Test() {
        Q84 q = new Q84();
        q.largestRectangleArea(new int[] {0,0,0,0,0,0,0,0,2147483647});
    }

    @Test
    public void q46Test() {
        Q46 q = new Q46();
        q.permute(new int[] {1, 2, 3});
    }

    @Test
    public void q17Test() {
        Q17 q = new Q17();
        q.letterCombinations("23");
    }

    @Test
    public void q218Test() {
        Q218 q = new Q218();
        int[][] b1 = new int[][] {
                new int[] {7,11,4},
                new int[] {8,11,3},
                new int[] {9,11,2},
                new int[] {10,11,1},
        };
        int[][] b2 = new int[][] {
                new int[] {997,1001,4},
                new int[] {998,1001,3},
                new int[] {999,1001,2},
                new int[] {1000,1001,1},
        };
        List<List<Integer>> ans = q.getSkylinePQ(b2);

        Integer a = 128;
        Integer b = 128;
        System.out.println(a == b);
    }

    @Test
    public void q707Test() {
        Q707 q = new Q707();
        q.addAtHead(1);
        q.addAtTail(3);
        q.addAtIndex(1, 2);
        q.get(1);
        q.deleteAtIndex(1);
        q.get(1);
    }
}