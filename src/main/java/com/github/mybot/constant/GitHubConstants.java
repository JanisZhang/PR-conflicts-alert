package com.github.mybot.constant;

/**
 * @author Janis Zhang
 */
public class GitHubConstants {
    public static final String CONFLICT_COMMENT =
            "<h3> ❌ Oops! Merge conflict alert! </h3><h4> Kindly resolve it " +
                    "for us to evaluate this PR. </h4>";
    public static final String LABEL_NAME = "Merge Conflicts";
    public static final int MAX_RETRY_TIMES = 5;
    public static final int RETRY_INTERVAL = 1000;
}


