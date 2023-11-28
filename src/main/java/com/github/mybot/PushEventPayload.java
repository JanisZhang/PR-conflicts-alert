package com.github.mybot;

import io.quarkiverse.githubapp.event.Push;
import org.jboss.logging.Logger;
import org.kohsuke.github.GHEventPayload;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHPullRequest;
import org.kohsuke.github.GHRepository;

import java.io.IOException;

/**
 * @author Janis Zhang
 */
public class PushEventPayload {

    void handlePushEvent(@Push GHEventPayload.Push pushPayload) {

        String ref = pushPayload.getRef();

        if (!(ref.equals("refs/heads/main") || ref.equals("refs/heads/master"))) {
            return;
        }

        GHRepository repository = pushPayload.getRepository();

        try {
            for (GHPullRequest pullRequest :
                    repository.getPullRequests(GHIssueState.OPEN)) {

                GithubMergeConflictsEventProcessor.processPullRequest(pullRequest);
            }
        } catch (IOException e) {

            logger.error("IOException: " + e +
                    "Couldn't get all PRs from repo: " + repository.getHtmlUrl());
        }

    }

    private static final Logger logger =
            Logger.getLogger(PushEventPayload.class);

}
