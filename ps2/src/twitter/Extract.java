package twitter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.*;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {

    /**
     * Get the time period spanned by tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return a minimum-length time interval that contains the timestamp of
     *         every tweet in the list.
     */

    public static Timespan getTimespan(List<Tweet> tweets) {

        assert EnsureTweetIdsAreDifferent(tweets);

        List inst = new ArrayList<Instant>();
        Instant lowestInst = tweets.get(0).getTimestamp();
        Instant highestInst = tweets.get(0).getTimestamp();

        for (Tweet t : tweets) {
            if (t.getTimestamp().isAfter(highestInst))
                highestInst = t.getTimestamp();
            if (t.getTimestamp().isBefore(lowestInst))
                lowestInst = t.getTimestamp();
        }
        return new Timespan(lowestInst, highestInst);

    }

    /**
     * Get usernames mentioned in a list of tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return the set of usernames who are mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getAuthor()'s spec). The username-mention cannot
     *         be immediately preceded or followed by any character valid in a
     *         Twitter username. For this reason, an email address like
     *         bitdiddle@mit.edu does NOT contain a mention of the username mit.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     */
    public static Set<String> getMentionedUsers(List<Tweet> tweets) {

        assert EnsureTweetIdsAreDifferent(tweets);

        StringBuilder sb = new StringBuilder();
        Set<String> set_authors = new HashSet<String>();
        for (Tweet t : tweets) {
            set_authors.add(t.getAuthor());
            sb.append(t.getText()).append(" ");
        }
        String fullTweets = sb.toString();
        System.out.println(fullTweets);

        Set<String> set__mentionned_names = new HashSet<String>();
        for (String auth : set_authors) {
            // Pattern pattern = Pattern.compile("[^\\w\\s]*@"+auth
            // +"[^\\w]*$");
            Pattern pattern = Pattern.compile("[^\\w-]*@" + auth + "[^\\w-]*");
            Matcher matcher = pattern.matcher(fullTweets);

            if (matcher.find()) {
                System.out.println(auth + "  Trouvé ! " + matcher.group());
                set__mentionned_names.add(auth);
            }

        }

        return set__mentionned_names;

    }

    
    // defensive method
    public static boolean EnsureTweetIdsAreDifferent(List<Tweet> tweets) {
        // assert all ids are different
        Set<Long> set_ids = new HashSet<Long>();
        for (Tweet t : tweets) {
            set_ids.add(t.getId());
        }
        return tweets.size() == set_ids.size();
    }

    /*
     * Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */
}
