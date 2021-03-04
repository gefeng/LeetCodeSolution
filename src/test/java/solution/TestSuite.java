package solution;

import annotations.Problem;
import org.junit.jupiter.api.Test;

import java.sql.PseudoColumnUsage;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Test
    public void q421Test() {
        Q421 q = new Q421();
        assertEquals(28, q.findMaximumXOR(new int[] {3, 10, 5, 25, 2, 8}));
    }

    @Test
    public void q212Test() {
        char[][] board = new char[][] { {'b','b','a','a','b','a'}, {'b','b','a','b','a','a'}, {'b','b','b','b','b','b'}, {'a','a','a','b','a','a'}, {'a','b','a','a','b','b'} };
        String[] words = new String[] {"abbbababaa"};
        Q212 q = new Q212();
        List<String> ans = q.findWords(board, words);
        System.out.println(ans.get(0));
    }

    @Test
    public void q425Test() {
        Q425 q = new Q425();
        String[] words = new String[] {"area","lead","wall","lady","ball"};
        //String[] words = new String[] {"ulus","mity","wind","chip","pill","pugh","flux","crib","sump","piss","fils","high","pipy","rusk","cuss","miri","pung","this","knit","hisn","zins","puns","tuff","ruth","whit","wild","burd","hubs","grin","kirs","zips","migg","lump","dint","jiff","spud","pith","rill","twit","pugs","ichs","jugs","simp","crus","bury","lisp","bund","fugs","prig","dusk","dirt","inns","mild","dups","hins","nigh","ring","muds","bisk","spin","tuts","puff","jill","grig","gist","bilk","gill","buck","slur","limn","firn","surf","girl","brit","ilks","typy","yirr","whir","undy","nill","rifs","husk","flus","sift","bids","swig","fuds","bush","birr","buff","buds","sims","ywis","suck","slit","irid","guck","fist","kris","dunk","didy","iffy","snub","luny","dull","stub","spic","buts","viny","bris","tump","phut","will","guff","putt","whid","tilt","slub","sris","pfft","mull","bill","turk","kith","grip","stun","hilt","skip","piny","curl","liri","lust","mirk","birl","musk","huts","tiff","tuns","ruin","burs","girn","juju","fuji","writ","suqs","much","iglu","lulu","bulb","gild","whig","yips","lips","riff","libs","gird","tils","whin","thru","tubs","hint","mumm","till","grid","bird","curb","rung","flit","glug","gimp","fink","sins","find","tick","mill","null","flip","cigs","subs","pits","tipi","zinc","skid","plus","grit","gnus","curf","turn","tiny","miff","gibs","nick","shit","linn","tint","bull","urbs","immy","gush","fury","tins","duff","wiss","pick","chum","junk","vugs","limb","sulk","kilt","buss","curr","ping","snug","tidy","khis","mids","with","frug","jinn","yill","fill","gulf","mush","list","swum","kink","cwms","quid","lunk","chug","urus","sulu","lutz","just","funk","firs","mixt","guts","gyps","yids","brin","silt","wigs","gigs","diss","mitt","hung","ribs","bhut","drum","pups","idly","pins","titi","ritz","slim","spik","furl","tics","sirs","must","shul","tips","burr","shin","suss","rush","lull","lift","zits","milk","puds","phiz","mick","tirl","nidi","film","ruts","stir","lung","pulp","lunt","ughs","sips","mibs","pily","kirn","glim","pirn","silk","luff","syph","birk","thin","butt","hums","smug","twin","kits","rink","tuft","wins","wuss","lush","tivy","unit","dump","duly","pity","hulk","trig","grub","curn","duns","kids","wist","tsks","flic","gits","mini","wing","zing","frig","lick","nixy","swim","yins","kiwi","nuns","cups","jism","vigs","puri","nurl","impi","quit","inly","syli","mugs","lurk","rims","spiv","duds","glum","dumb","rump","fixt","tyin","dips","bibs","wits","chub","king","buhr","virl","busy","scut","urns","scum","curs","gull","idyl","sync","smut","slum","sups","blin","suds","bubs","crud","firm","djin","sung","rich","runs","runt","suit","wink","bunn","pull","brut","slut","jibs","figs","gins","digs","wyns","hind","gips","mump","jigs","spur","dims","wynn","hips","nuts","dugs","ruck","sums","ding","nisi","girt","hunt","vill","clit","mutt","umps","gulp","puny","buys","trug","guid","duty","dits","spit","dung","yuch","guls","fumy","liny","prim","scud","shun","durr","ling","muns","rust","quiz","jibb","fuci","inti","guys","dirk","ills","whys","scry","ziti","crux","kind","huic","glut","sink","fubs","bind","ting","pigs","turd","tuis","gift","iwis","putz","kick","muss","mist","chid","kifs","ruly","mink","punk","skis","burn","lint","jump","pump","curt","bums","dink","murr","pimp","huns","fuzz","rimy","hull","yuks","fuck","fins","kuru","sudd","cuts","dipt","wynd","stum","city","nims","wily","sibs","unci","isms","psst","jink","bunt","pyic","ugly","sith","funs","myth","fibs","kudu","gilt","fuss","hunh","yurt","muts","rigs","rins","inks","rick","hiss","irks","puls","jinx","bulk","curd","purr","trip","such","hili","muck","hugs","vims","vugg","puss","limy","mils","midi","vugh","drub","hill","purl","huck","imid","duct","chit","miry","muni","litu","rift","kiln","buns","kips","tits","bitt","chis","fids","nurd","slip","rips","whiz","sics","brig","rhus","sing","dish","huff","rubs","sugh","sill","punt","lits","hurt","wiry","skim","hunk","cunt","plum","sunn","luvs","muff","brim","fizz","drib","piki","rynd","shiv","fisc","kirk","quin","sinh","pyin","purs","thud","knur","migs","grum","bins","burl","spun","blip","wych","cist","blub","icky","slid","wimp","lynx","tush","yuck","tusk","snit","hits","bits","grim","hump","mirs","mums","clip","fugu","skin","jury","cubs","pips","whip","pics","typp","lily","thir","mumu","smit","disc","pfui","blur","hymn","puts","wish","pink","bibb","gyms","bigs","tung","hick","puck","milt","itch","mind","cusk","uric","furs","guvs","guns","gink","nits","lich","shri","futz","duck","cris","duci","drip","illy","burp","minx","sild","brrr","lids","yird","guru","phis","wilt","fish","luck","link","psis","plug","bump","dins","lugs","burg","tuck","kill","jilt","wisp","drug","rugs","dust","shim","jimp","duit","push","pint","dill","disk","gyri","rind","lums","mint","ghis","rids","sizy","club","slug","scup","xyst","limp","imps","tups","sibb","cuff","tiki","stud","sits","nips","trim","bust","gums","wick","turf","fund","snip","cuds","fits","chin","tugs","inch","pish","hurl","thus","glib","murk","quip","flub","drys","dibs","pili","hyps","ruby","dick","bint","lins","wich","buhl","urds","zill","biff","kist","ibis","byrl","dubs","cull","puli","bumf","juts","gids","durn","surd","twig","friz","cusp","yups","hist","skit","kins","miss","nubs","chic","lilt","buzz","sign","frit","tutu","culm","sigh","whim","hush","bugs","winy","busk","inky","gunk","numb","sick","cult","risk","rudd","bunk","iris","ditz","inby","mugg","bung","zigs","rums","dirl","nils","pubs","jins","nibs","kiss","full","dunt","sunk","ship","pixy","suns","cyst","ruff","gust","thug","cuif","spry","snib","upby","shut"};
        q.wordSquares(words);
    }

    @Test
    public void q336Test() {
        Q336 q = new Q336();
        String[] words = new String[] {"abcd","dcba","lls","s","sssll",""};
        q.palindromePairs(words);
    }

    @Test
    public void q1089Test() {
        Q1089 q = new Q1089();
        q.duplicateZeros(new int[] {9,9,9,4,8,0,0,3,7,2,0,0,0,0,9,1,0,0,1,1,0,5,6,3,1,6,0,0,2,3,4,7,0,3,9,3,6,5,8,9,1,1,3,2,0,0,7,3,3,0,5,7,0,8,1,9,6,3,0,8,8,8,8,0,0,5,0,0,0,3,7,7,7,7,5,1,0,0,8,0,0});
    }

    @Test
    public void q322Test() {
        Q322 q = new Q322();
        q.findItinerary(Arrays.asList(Arrays.asList("AXA","EZE"),Arrays.asList("EZE","AUA"),Arrays.asList("ADL","JFK"),Arrays.asList("ADL","TIA"),Arrays.asList("AUA","AXA"),Arrays.asList("EZE","TIA"),
                Arrays.asList("EZE","TIA"),Arrays.asList("AXA","EZE"),Arrays.asList("EZE","ADL"),Arrays.asList("ANU","EZE"),Arrays.asList("TIA","EZE"),Arrays.asList("JFK","ADL"),Arrays.asList("AUA","JFK"),Arrays.asList("JFK","EZE"),Arrays.asList("EZE","ANU"), 
                Arrays.asList("ADL","AUA"),Arrays.asList("ANU","AXA"),Arrays.asList("AXA","ADL"),Arrays.asList("AUA","JFK"),Arrays.asList("EZE","ADL"),Arrays.asList("ANU","TIA"),Arrays.asList("AUA","JFK"),Arrays.asList("TIA","JFK"),Arrays.asList("EZE","AUA"),
                Arrays.asList("AXA","EZE"),Arrays.asList("AUA","ANU"),Arrays.asList("ADL","AXA"),Arrays.asList("EZE","ADL"),Arrays.asList("AUA","ANU"),Arrays.asList("AXA","EZE"),Arrays.asList("TIA","AUA"),Arrays.asList("AXA","EZE"),Arrays.asList("AUA","SYD"),
                Arrays.asList("ADL","JFK"),Arrays.asList("EZE","AUA"),Arrays.asList("ADL","ANU"),Arrays.asList("AUA","TIA"),Arrays.asList("ADL","EZE"),Arrays.asList("TIA","JFK"),Arrays.asList("AXA","ANU"),Arrays.asList("JFK","AXA"),Arrays.asList("JFK","ADL"),
                Arrays.asList("ADL","EZE"),Arrays.asList("AXA","TIA"),Arrays.asList("JFK","AUA"),Arrays.asList("ADL","EZE"),Arrays.asList("JFK","ADL"),Arrays.asList("ADL","AXA"),Arrays.asList("TIA","AUA"),Arrays.asList("AXA","JFK"),Arrays.asList("ADL","AUA"),
                Arrays.asList("TIA","JFK"),Arrays.asList("JFK","ADL"),Arrays.asList("JFK","ADL"),Arrays.asList("ANU","AXA"),Arrays.asList("TIA","AXA"),Arrays.asList("EZE","JFK"),Arrays.asList("EZE","AXA"),Arrays.asList("ADL","TIA"),Arrays.asList("JFK","AUA"),
                Arrays.asList("TIA","EZE"),Arrays.asList("EZE","ADL"),Arrays.asList("JFK","ANU"),Arrays.asList("TIA","AUA"),Arrays.asList("EZE","ADL"),Arrays.asList("ADL","JFK"),Arrays.asList("ANU","AXA"),Arrays.asList("AUA","AXA"),Arrays.asList("ANU","EZE"),
                Arrays.asList("ADL","AXA"),Arrays.asList("ANU","AXA"),Arrays.asList("TIA","ADL"),Arrays.asList("JFK","ADL"),Arrays.asList("JFK","TIA"),Arrays.asList("AUA","ADL"),Arrays.asList("AUA","TIA"),Arrays.asList("TIA","JFK"),Arrays.asList("EZE","JFK"),
                Arrays.asList("AUA","ADL"),Arrays.asList("ADL","AUA"),Arrays.asList("EZE","ANU"),Arrays.asList("ADL","ANU"),Arrays.asList("AUA","AXA"),Arrays.asList("AXA","TIA"),Arrays.asList("AXA","TIA"),Arrays.asList("ADL","AXA"),Arrays.asList("EZE","AXA"),
                Arrays.asList("AXA","JFK"),Arrays.asList("JFK","AUA"),Arrays.asList("ANU","ADL"),Arrays.asList("AXA","TIA"),Arrays.asList("ANU","AUA"),Arrays.asList("JFK","EZE"),Arrays.asList("AXA","ADL"),Arrays.asList("TIA","EZE"),Arrays.asList("JFK","AXA"),
                Arrays.asList("AXA","ADL"),Arrays.asList("EZE","AUA"),Arrays.asList("AXA","ANU"),Arrays.asList("ADL","EZE"),Arrays.asList("AUA","EZE")));
    }

    @Test
    public void q675() {
        Q675 q = new Q675();
        List<List<Integer>> forest = new ArrayList<>();
        forest.add(Arrays.asList(69438,55243,0,43779,5241,93591,73380));
        forest.add(Arrays.asList(847,49990,53242,21837,89404,63929,48214));
        forest.add(Arrays.asList(90332,49751,0,3088,16374,70121,25385));
        forest.add(Arrays.asList(14694,4338,87873,86281,5204,84169,5024));
        forest.add(Arrays.asList(31711,47313,1885,28332,11646,42583,31460));
        forest.add(Arrays.asList(59845,94855,29286,53221,9803,41305,60749));
        forest.add(Arrays.asList(95077,50343,27947,92852,0,0,19731));
        forest.add(Arrays.asList(86158,63553,56822,90251,0,23826,17478));
        forest.add(Arrays.asList(60387,23279,78048,78835,5310,99720,0));
        forest.add(Arrays.asList(74799,48845,60658,29773,96129,90443,14391));
        forest.add(Arrays.asList(65448,63358,78089,93914,7931,68804,72633));
        forest.add(Arrays.asList(93431,90868,55280,30860,59354,62083,47669));
        forest.add(Arrays.asList(81064,93220,22386,22341,95485,20696,13436));
        forest.add(Arrays.asList(50083,0,89399,43882,0,13593,27847));
        forest.add(Arrays.asList(0,12256,33652,69301,73395,93440,0));
        forest.add(Arrays.asList(42818,87197,81249,33936,7027,5744,64710));
        forest.add(Arrays.asList(35843,0,99746,52442,17494,49407,63016));
        forest.add(Arrays.asList(86042,44524,0,0,26787,97651,28572));
        forest.add(Arrays.asList(54183,83466,96754,89861,84143,13413,72921));
        forest.add(Arrays.asList(89405,52305,39907,27366,14603,0,14104));
        forest.add(Arrays.asList(70909,61104,70236,30365,0,30944,98378));
        forest.add(Arrays.asList(20124,87188,6515,98319,78146,99325,88919));
        forest.add(Arrays.asList(89669,0,64218,85795,2449,48939,12869));
        forest.add(Arrays.asList(93539,28909,90973,77642,0,72170,98359));
        forest.add(Arrays.asList(88628,16422,80512,0,38651,50854,55768));
        forest.add(Arrays.asList(13639,2889,74835,80416,26051,78859,25721));
        forest.add(Arrays.asList(90182,23154,16586,0,27459,3272,84893));
        forest.add(Arrays.asList(2480,33654,87321,93272,93079,0,38394));
        forest.add(Arrays.asList(34676,72427,95024,12240,72012,0,57763));
        forest.add(Arrays.asList(97957,56,83817,45472,0,24087,90245));
        forest.add(Arrays.asList(32056,0,92049,21380,4980,38458,3490));
        forest.add(Arrays.asList(21509,76628,0,90430,10113,76264,45840));
        forest.add(Arrays.asList(97192,58807,74165,65921,45726,47265,56084));
        forest.add(Arrays.asList(16276,27751,37985,47944,54895,80706,2372));
        forest.add(Arrays.asList(28438,53073,0,67255,38416,63354,69262));
        forest.add(Arrays.asList(23926,75497,91347,58436,73946,39565,10841));
        forest.add(Arrays.asList(34372,69647,44093,62680,32424,69858,68719));
        forest.add(Arrays.asList(24425,4014,94871,1031,99852,88692,31503));
        forest.add(Arrays.asList(24475,12295,33326,37771,37883,74568,25163));
        forest.add(Arrays.asList(0,18411,88185,60924,29028,69789,0));
        forest.add(Arrays.asList(34697,75631,7636,16190,60178,39082,7052));
        forest.add(Arrays.asList(24876,9570,53630,98605,22331,79320,88317));
        forest.add(Arrays.asList(27204,89103,15221,91346,35428,94251,62745));
        forest.add(Arrays.asList(26636,28759,12998,58412,38113,14678,0));
        forest.add(Arrays.asList(80871,79706,45325,3861,12504,0,4872));
        forest.add(Arrays.asList(79662,15626,995,80546,64775,0,68820));
        forest.add(Arrays.asList(25160,82123,81706,21494,92958,33594,5243));

        System.out.println(q.cutOffTree(forest));
    }
}