package DesignAlgoJava;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class TwitterTweetFeedFollow {
    public static void main(String[] args) {
        TwitterTweetFeedFollow twitter = new TwitterTweetFeedFollow();
        System.out.println("twitter.postTweet(1, 5)");
        twitter.postTweet(1, 5);
        System.out.println("twitter.getNewsFeed(1): " + twitter.getNewsFeed(1));
        System.out.println("twitter.follow(1, 2)");
        twitter.follow(1, 2);
        System.out.println("twitter.postTweet(2, 6)");
        twitter.postTweet(2, 6);
        System.out.println("twitter.getNewsFeed(1): " + twitter.getNewsFeed(1));
        twitter.unfollow(1, 2);
        System.out.println("twitter.unfollow(1, 2)");
        System.out.println("twitter.getNewsFeed(1): " + twitter.getNewsFeed(1));
    }

    Map<Integer, List<Tweet>> tweets = new HashMap<>(); // userid -> user's tweets
    Map<Integer, Set<Integer>> followees = new HashMap<>(); // userid -> user's followees

    TwitterTweetFeedFollow() {
        tweets = new HashMap<>();
        followees = new HashMap<>();
    }

    private void postTweet(int userId, int tweetId) {
        if (!tweets.containsKey(userId)) {
            tweets.put(userId, new LinkedList<>());
        }
        tweets.get(userId).add(0, new Tweet(tweetId));
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed.
     * Each item in the news feed must be posted by users
     * who the user followed or by the user herself.
     * Tweets must be ordered from most recent to least recent.
     */
    private List<Integer> getNewsFeed(int userId) {
        Queue<Feed> q = new PriorityQueue<>(
                Comparator.comparing(f -> -f.curr.order)); // descending

        if (!tweets.getOrDefault(userId, Collections.emptyList()).isEmpty()) {
            q.offer(new Feed(tweets.get(userId)));
        }

        for (Integer followee : followees.getOrDefault(userId, Collections.emptySet())) {
            if (!tweets.getOrDefault(followee, Collections.emptyList()).isEmpty()) {
                q.offer(new Feed(tweets.get(followee)));
            }
        }

        List<Integer> feeds = new ArrayList<>();
        for (int i = 0; i < 10 && !q.isEmpty(); i++) {
            Feed feed = q.poll();
            feeds.add(feed.curr.id);

            if (feed.advance()) {
                q.offer(feed);
            }
        }

        return feeds;
    }

    /**
     * Follower follows a followee.
     * If the operation is invalid, it should be a no-op.
     */

    private void follow(int followerId, int followeeId) {
        if (followerId == followeeId) {
            return;
        }
        if (!followees.containsKey(followerId)) {
            followees.put(followerId, new HashSet<>());
        }
        followees.get(followerId).add(followeeId);
    }

    /**
     * Follower unfollows a followee.
     * If the operation is invalid, it should be a no-op.
     */
    private void unfollow(int followerId, int followeeId) {
        if (!followees.containsKey(followerId)) {
            return;
        }
        followees.get(followerId).remove(followeeId);
    }

    int globalOrder = 0;

    private class Tweet {
        int id;
        int order;

        Tweet(int id) {
            this.id = id;
            this.order = globalOrder++;
        }
    }

    private class Feed {
        Iterator<Tweet> iterator;
        Tweet curr;

        Feed(List<Tweet> tweets) {
            // tweets cannot be empty
            iterator = tweets.iterator();
            curr = iterator.next();
        }

        boolean advance() {
            if (!iterator.hasNext()) {
                return false;
            }
            this.curr = iterator.next();
            return true;
        }
    }
}
