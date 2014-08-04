package com.ehc.test;

import org.kohsuke.github.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: ehc
 * Date: 2/8/14
 * Time: 4:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    public static String OAuthToken = "bc985cb63621207b3168779917278b82e9e6685c";

    public static void main(String args[]) {
        String sha1 = "";
        try {
            GitHub hub = GitHub.connectUsingOAuth(OAuthToken);
            GHOrganization organization = hub.getOrganization("ehc");
            Map<String, GHTeam> teams = organization.getTeams();
            System.out.println("--------------------------");
            for (Map.Entry<String, GHTeam> entry : teams.entrySet()) {
                System.out.println(entry.getKey() + "/" + entry.getValue());
                Set<GHUser> users = entry.getValue().getMembers();
                for (GHUser user : users) {
                    System.out.println("TeamMembers: " + user);
                }
            }
            System.out.println("--------------------------");

            Map<String, GHRepository> repositories = organization.getRepositories();
            for (Map.Entry<String, GHRepository> repositoryEntry : repositories.entrySet()) {
                System.out.println("--------------------------");
                System.out.println(repositoryEntry.getKey() + " =/= " + repositoryEntry.getValue());
                System.out.println("Repository: " + repositoryEntry.getKey());

                for (String names : repositoryEntry.getValue().getCollaboratorNames()) {
                    System.out.println("Name: " + names);
                }
                for (GHRef ref : repositoryEntry.getValue().getRefs()) {
                    String branch = ref.getRef();

                    System.out.println("branch: " + branch);

                    String sha = ref.getObject().getSha();
                    System.out.println("sha: " + sha);


                    for (Map.Entry<String, GHBranch> branchEntry : repositoryEntry.getValue().getBranches().entrySet()) {
                        System.out.println(branchEntry.getKey() + "=/=" + branchEntry.getValue());
                        sha1 = branchEntry.getValue().getSHA1();
                        System.out.println("sha1: " + sha1);
                    }

                    GHCommit commit = repositoryEntry.getValue().getCommit(sha1);
                    List<GHCommit> commits = commit.getParents();
                    System.out.println("ParentCommits: " + commits.toString());
                    System.out.println("commit: " + commit.toString());
                    System.out.println("author: " + commit.getAuthor().getName());
                    if (commit.getCommitter().toString() != null)
                        System.out.println("Committer: " + commit.getCommitter().toString());
                }
            }
            System.out.println("--------------------------");


        } catch (IOException e) {
            System.out.println("Exception: " + e.getCause());
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
