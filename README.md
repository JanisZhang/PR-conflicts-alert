<p align="center">
  <img src="https://gist.githack.com/JanisZhang/e072587fe7c765e2b7744adc34c2a56a/raw/a3bd36899273780c471efbee1bde1442bef12dbe/logo.png">
</p>

⭐ [Star this project](https://github.com/JanisZhang/PR-conflicts-alert)

## Introduction

The [PR Conflicts Alert](https://github.com/JanisZhang/PR-conflicts-alert) GitHub App, powered by [Quarkus](https://quarkus.io/), it auto-labels PRs encountering conflicts, removing the label post-resolution.

## Getting Started

- add a `Merge Conflicts` label to repo.
-  [Install](https://github.com/apps/pr-conflicts-alert).

## Why
PR conflicts often arose due to unnoticed changes or outdated code, causing delays in the review process. By automating conflict detection and providing immediate alerts, this app fosters smoother collaboration between reviewers and developers, effectively addressing these issues.


## Features

The app triggers during the following events:

- Opening or reopening a pull request
- Synchronization of pull requests (force-push)
- When pushing new code to the master/main branch, it monitors all open pull requests for conflicts.

## Author

**GitHub**: [Janis Zhang](https://github.com/JanisZhang)
