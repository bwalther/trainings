package heap;

import java.util.*;

public class Twitter {
    public static void main(String[] args) {
        Twitter t = new Twitter();
        t.postTweet(1, 5);
        t.getNewsFeed(1);
        t.follow(1, 2);
        t.postTweet(2, 6);
        t.getNewsFeed(1);
        t.unfollow(1, 2);
        t.getNewsFeed(1);
    }

    static int simpleTs = 0;

    Map<Integer, List<Tweet>> tweets = new HashMap<>(); // userId -> tweets
    Map<Integer, Set<Integer>> userFollows = new HashMap<>();

    class Tweet {
        int userId;
        int tweetId;
        int ts;

        public Tweet(int aUserId, int aTweetId, int ats) {
            this.userId = aUserId;
            this.tweetId = aTweetId;
            this.ts = ats;
        }
    }

    public Twitter() {

    }

    public void postTweet(int userId, int tweetId) {
        Tweet tweet = new Tweet(userId, tweetId, simpleTs++);
        tweets.computeIfAbsent(userId, k -> new ArrayList<>()).add(tweet);
    }


    public List<Integer> getNewsFeed(int userId) {
        Set<Integer> followers = new HashSet<>();
        followers.add(userId);
        if (userFollows.containsKey(userId)) {
            followers.addAll(userFollows.get(userId));
        }
        List<Tweet> followerTweets = new ArrayList<>();
        for (Integer fId : followers) {
            if (tweets.containsKey(fId)) {
                followerTweets.addAll(tweets.get(fId));
            }
        }
        PriorityQueue<Tweet> pq = new PriorityQueue<>((a, b) -> -Integer.compare(a.ts, b.ts));
        pq.addAll(followerTweets);
        List<Integer> res = new ArrayList<>();
        while (!pq.isEmpty() && res.size() <= 10) {
            res.add(pq.poll().tweetId);
        }
        return res;
    }

    public void follow(int followerId, int followeeId) {
        userFollows.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if (userFollows.containsKey(followerId)) {
            userFollows.get(followerId).remove(followeeId);
        }
    }
}
