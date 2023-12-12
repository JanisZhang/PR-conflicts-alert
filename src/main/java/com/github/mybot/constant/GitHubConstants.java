package com.github.mybot.constant;

/**
 * @author Janis Zhang
 */
public class GitHubConstants {
    public static final String CONFLICT_COMMENT = "Please resolve " +
            "**conflicts** so we can continue reviewing and processing." +
            "\n\nCreated by" +
            " [<img src=\"https://gistcdn.githack" +
            ".com/JanisZhang/68c43cbcad79a804f634aa6d43494573/raw/956722637be983f28c546232ca3b5184f137865c/logo.png\" valign=\"bottom\"/> " +
            "**merge_conflicts_alert[bot]**](https://github" +
            ".com/apps/merge-conflicts-alert)";

    public static final String LABEL_NAME = "Merge Conflicts";
    public static final int MAX_RETRY_TIMES = 10;
    public static final int RETRY_INTERVAL = 1000;
}


