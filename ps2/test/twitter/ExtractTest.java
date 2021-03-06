package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class ExtractTest {

    /*
     * TODO: your testing strategies for these methods should go here. Testing
     * startegies: For getTimespan() Partitions number of tweet ids = 2, >2 odd
     * , and >3 even tweets.timestamps have equal values, different values
     * 
     * For getMentionedUsers(List<Tweet> tweets) Partitions : tweets not
     * mentionning users, mentioning users one time/two times with mention @user
     * , NOTVALIDCHAR@userNOTVALIDCHAR
     */

    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    private static final Instant d3 = Instant.parse("2016-02-17T12:00:00Z");
    private static final Instant d4 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d5 = Instant.parse("2016-02-17T13:00:00Z");

    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);
    private static final Tweet tweet3 = new Tweet(3, "bbitdiddle", "rivest talk in 30 minutes #hype", d3);
    private static final Tweet tweet4 = new Tweet(4, "alyssa",
            "is it @alyssa  reasonable .@alyssa to talk about rivest so much?", d4);
    private static final Tweet tweet5 = new Tweet(5, "alyssa",
            "is it @alyssa  reasonable .@bbitdiddle to talk about rivest so much?", d5);

    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    @Test
    public void testGetTimespanTwoTweets() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2));

        assertEquals("expected start", d1, timespan.getStart());
        assertEquals("expected end", d2, timespan.getEnd());
    }

    @Test
    public void testGetTimespanTwoTweetsEqualInstants() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet4));

        assertEquals("expected start", d1, timespan.getStart());
        assertEquals("expected end", d4, timespan.getEnd());
    }

    @Test
    public void testGetTimespanThreeTweets() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2, tweet3));

        assertEquals("expected start", d1, timespan.getStart());
        assertEquals("expected end", d3, timespan.getEnd());
    }

    @Test
    public void testGetTimespanThreeTweetsWithTwoEquals() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2, tweet4));

        assertEquals("expected start", d1, timespan.getStart());
        assertEquals("expected end", d2, timespan.getEnd());
    }

    @Test
    public void testGetMentionedUsersNoMention() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1));

        assertTrue("expected empty set", mentionedUsers.isEmpty());
    }

    @Test
    public void testGetMentionedUsersOneMentionOneTime() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1, tweet4));

        assertTrue("expected 1 mentionned users ", mentionedUsers.size() == 1);
    }

    @Test
    public void testGetMentionedUsersTwoMentions() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1, tweet2, tweet3, tweet4, tweet5));

        assertTrue("expected 2 mentionned users ", mentionedUsers.size() == 2);
    }
    
    
    
    /*
     * Warning: all the tests you write here must be runnable against any
     * Extract class that follows the spec. It will be run against several staff
     * implementations of Extract, which will be done by overwriting
     * (temporarily) your version of Extract with the staff's version. DO NOT
     * strengthen the spec of Extract or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in Extract, because that means you're testing a
     * stronger spec than Extract says. If you need such helper methods, define
     * them in a different class. If you only need them in this test class, then
     * keep them in this test class.
     */

    /*
     * Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */

}
