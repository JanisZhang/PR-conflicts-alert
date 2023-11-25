package com.github.mybot;

import io.quarkiverse.githubapp.event.Push;
import org.kohsuke.github.GHEventPayload;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHPullRequest;
import org.kohsuke.github.GHRepository;

import java.io.IOException;

/**
 * @author Janis Zhang
 */
public class PushEventHandler {
    void handlePushEvent(@Push GHEventPayload.Push pushPayload) throws IOException {
        String ref = pushPayload.getRef();

        if(ref == null){
            return;
        }

        if(!(ref.equals("refs/heads/main") || ref.equals("refs/heads/master"))){
            return;
        }

        GHRepository repository = pushPayload.getRepository();

        for(GHPullRequest pullRequest: repository.getPullRequests(GHIssueState.OPEN)){
            GithubEventProcesser githubEventProcesser =
                    new GithubEventProcesser(pullRequest);

            githubEventProcesser.processPullRequest();
        }

    }

}
