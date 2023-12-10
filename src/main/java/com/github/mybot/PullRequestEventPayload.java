package com.github.mybot;

import io.quarkiverse.githubapp.event.PullRequest;
import org.jboss.logging.Logger;
import org.kohsuke.github.GHEventPayload;

import java.io.IOException;

/**
 * @author Janis Zhang
 */
public class PullRequestEventPayload {

    void handlePullRequestEvent(
            @PullRequest.Opened @PullRequest.Reopened @PullRequest.Synchronize
            GHEventPayload.PullRequest payload) {

        logger.info("Received Pull Request Event from repo: " + payload.getRepository());

        try {
            GithubMergeConflictsEventProcessor.processPullRequest(payload.getPullRequest());

        } catch (IOException e) {

            logger.error("IOException: " + e + "Couldn't get PR." );
        }
    }

    private static final Logger logger =
            Logger.getLogger(PullRequestEventPayload.class);
}
