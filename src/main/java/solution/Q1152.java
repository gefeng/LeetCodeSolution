package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Analyze User Website Visit Pattern",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/analyze-user-website-visit-pattern/"
)
public class Q1152 {
    private class VisitInfo {
        String website;
        int timestamp;
        VisitInfo(String website, int timestamp) {
            this.website = website;
            this.timestamp = timestamp;
        }
    }
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        int n = username.length;
        HashMap<String, List<VisitInfo>> userMap = new HashMap<>();
        for(int i = 0; i < n; i++) {
            String user = username[i];
            String site = website[i];
            int time = timestamp[i];
            if(!userMap.containsKey(user))
                userMap.put(user, new ArrayList<>());
            userMap.get(user).add(new VisitInfo(site, time));
        }

        HashMap<String, Integer> seqMap = new HashMap<>();
        for(String user : userMap.keySet()) {
            List<VisitInfo> visits = userMap.get(user);
            Collections.sort(visits, (a, b) -> {
                return a.timestamp - b.timestamp;
            });
            System.out.println(user);
            for(VisitInfo vi : visits)
                System.out.println(vi.website);
            findSequence(visits, 0, new ArrayList<>(), new HashSet<>(), seqMap);
        }

        int max = 0;
        String maxSeq = "";
        for(String key : seqMap.keySet()) {
            int count = seqMap.get(key);
            if(count > max) {
                max = count;
                maxSeq = key;
            } else if(count == max) {
                maxSeq = key.compareTo(maxSeq) < 0 ? key : maxSeq;
            }
        }

        return Arrays.asList(maxSeq.split("-"));
    }

    private void findSequence(List<VisitInfo> visits, int start, List<String> seq, HashSet<String> pattern, HashMap<String, Integer> seqMap) {
        for(int i = start; i < visits.size(); i++) {
            VisitInfo vi = visits.get(i);
            seq.add(vi.website);
            if(seq.size() == 3) {
                StringBuilder sb = new StringBuilder();
                for(String s : seq) {
                    sb.append(s);
                    sb.append("-");
                }
                String key = sb.toString();
                if(!pattern.contains(key)) {
                    pattern.add(key);
                    seqMap.put(key, seqMap.getOrDefault(key, 0) + 1);
                }
            } else
                findSequence(visits, i + 1, seq, pattern, seqMap);
            seq.remove(seq.size() - 1);
        }
    }
}
