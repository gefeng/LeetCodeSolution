package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Design Twitter",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DESIGN,
        url = "https://leetcode.com/problems/design-twitter/"
)
public class Q355 {
    private class Tweet {
        int id;
        int user;
        Tweet(int user, int id) {
            this.id = id;
            this.user = user;
        }
    }

    private LinkedList<Tweet> tweets;
    private HashMap<Integer, Set<Integer>> fMap;
    /** Initialize your data structure here. */
    public Q355() {
        tweets = new LinkedList<>();
        fMap = new HashMap<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        tweets.addFirst(new Tweet(userId, tweetId));
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> news = new ArrayList<>();
        Set<Integer> followees = new HashSet<>();
        if(fMap.containsKey(userId))
            followees = fMap.get(userId);

        for(Tweet t : tweets) {
            if(t.user == userId || followees.contains(t.user)) {
                news.add(t.id);
                if(news.size() == 10)
                    break;
            }
        }

        return news;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if(!fMap.containsKey(followerId))
            fMap.put(followerId, new HashSet<>());
        fMap.get(followerId).add(followeeId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(fMap.containsKey(followerId)) {
            Set<Integer> followees = fMap.get(followerId);
            if(followees.contains(followeeId))
                followees.remove(followeeId);
        }
    }
}
