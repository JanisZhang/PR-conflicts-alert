package com.github.mybot;

import com.github.mybot.constant.GitHubConstants;
import org.kohsuke.github.*;

import java.io.IOException;

/**
 * @author Janis Zhang
 */
public class GithubEventProcesser {
    public GithubEventProcesser(GHPullRequest pullRequest){
        _pullRequest = pullRequest;
    }

    public void processPullRequest() throws IOException {

        if(!_isPullRequestMergeable()){
            _addLabelToRepo();

            return;
        }

        if(_isPullRequestLabeled()){
           _removeLabel();
        }
    }

    private void _removeLabel()
            throws IOException {

        _pullRequest.removeLabel(GitHubConstants.LABEL_NAME);
    }

    private Boolean _isPullRequestLabeled() {

        for(GHLabel label: _pullRequest.getLabels()){
            if(GitHubConstants.LABEL_NAME.equals(label.getName())){
                return true;
            }
        }
        return false;
    }

    private Boolean _isPullRequestMergeable()
            throws IOException {
        return _pullRequest.getMergeable();
    }
    private void _addLabelToRepo()
            throws IOException {
        GHRepository repository = _pullRequest.getRepository();

        if(_isPullRequestLabeled()){
            return;
        }

        boolean labelExistedInRepo = false;

        for(GHLabel label:repository.listLabels().toList()){
            if(GitHubConstants.LABEL_NAME.equals(label.getName())){
                labelExistedInRepo = true;

                break;
            }
        }

        if(!labelExistedInRepo){
            //ToDo: exception
            repository.createLabel(GitHubConstants.LABEL_NAME,
                    GitHubConstants.LABEL_COLOR, GitHubConstants.LABEL_DESC);
        }

        _pullRequest.addLabels(GitHubConstants.LABEL_NAME);
        _pullRequest.comment(GitHubConstants.CONFLICT_COMMENT);
    }

    private final GHPullRequest _pullRequest;

}
