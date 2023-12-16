package com.github.mybot;

import com.github.mybot.constant.GitHubConstants;
import org.jboss.logging.Logger;
import org.kohsuke.github.*;

import java.io.IOException;

/**
 * @author Janis Zhang
 */
public class GithubMergeConflictsEventProcessor {

    public static void processPullRequest(GHPullRequest pullRequest)
            throws IOException {

        _pullRequest = pullRequest;

        Boolean mergeStatus = _isPullRequestMergeable();

        if (mergeStatus == null) {
            return;
        }

        if (!mergeStatus) {
            _notifyIfPRNotMerageable();

            return;
        }

        if (_isPullRequestLabeled()) {
           _removeLabel();
        }
    }

    private static void _removeLabel() {

        try {
            _pullRequest.removeLabel(GitHubConstants.LABEL_NAME);

        } catch (IOException e) {

            logger.error("IOException: " + e +
                    "Couldn't remove label in PR:" + _pullRequest.getHtmlUrl());
        }
    }

    private static Boolean _isPullRequestLabeled() {

        for (GHLabel label : _pullRequest.getLabels()) {
            if (GitHubConstants.LABEL_NAME.equals(label.getName())) {
                return true;
            }
        }

        return false;
    }

    private static Boolean _isPullRequestMergeable() {

        int retry_count = 0;

        try {
            while (retry_count < GitHubConstants.MAX_RETRY_TIMES) {
                Boolean mergeStatus = _pullRequest.getMergeable();

                if (mergeStatus != null) {
                    return mergeStatus;
                }

                Thread.sleep(GitHubConstants.RETRY_INTERVAL);

                retry_count++;
            }

            logger.error("Reached max retry times in pr: "
                    + _pullRequest.getHtmlUrl());

            return null;

        } catch (IOException | InterruptedException e) {

            logger.error("Couldn't obtain merge-able status: "+ e);
        }

        return true;
    }


    private static void _notifyIfPRNotMerageable() {

        GHRepository repository = _pullRequest.getRepository();

        if (_isPullRequestLabeled()) {
            return;
        }

        try {
            for (GHLabel label : repository.listLabels().toList()) {
                if (GitHubConstants.LABEL_NAME.equals(label.getName())) {
                    _pullRequest.addLabels(GitHubConstants.LABEL_NAME);

                    break;
                }
            }

            _pullRequest.comment(GitHubConstants.CONFLICT_COMMENT);

        } catch (IOException e) {

            logger.error("IOException: " + e +
                    "Couldn't notify PR:" + _pullRequest.getHtmlUrl());
        }
    }

    private static GHPullRequest _pullRequest;

    private static final Logger logger =
            Logger.getLogger(GithubMergeConflictsEventProcessor.class);

}
