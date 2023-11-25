package com.github.mybot;

import io.quarkiverse.githubapp.event.PullRequest;
import org.kohsuke.github.GHEventPayload;

import java.io.IOException;

/**
 * @author Janis Zhang
 */
public class PullRequestEventHandler {
    //Todo: need to test whether @PullRequest.Synchronize can handle force-push
    void handlePullRequestEvent(
            @PullRequest.Opened @PullRequest.Reopened @PullRequest.Synchronize
            GHEventPayload.PullRequest pullRequestPayload) throws IOException {

        GithubEventProcesser githubEventProcesser =
                new GithubEventProcesser(pullRequestPayload.getPullRequest());

        githubEventProcesser.processPullRequest();
    }
}
