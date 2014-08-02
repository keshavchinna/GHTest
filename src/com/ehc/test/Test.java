package com.ehc.test;

import org.kohsuke.github.*;

import java.io.IOException;
import java.util.Iterator;
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
    try {
      GitHub hub = GitHub.connectUsingOAuth(OAuthToken);
      GHRepository repo = hub.getRepository("preym/OAuth");
      GHOrganization organization = hub.getOrganization("ehc");
      Map<String, GHTeam> teams = organization.getTeams();
      Iterator entries = teams.entrySet().iterator();
      while (entries.hasNext()) {
        Map.Entry entry = (Map.Entry) entries.next();
        String key = (String) entry.getKey();
        GHTeam value = (GHTeam) entry.getValue();
        Set<GHUser> users = value.getMembers();
        System.out.println("Key = " + key + ", Value = " + value);
        System.out.println("Developers/Team Members:" + users.toString());
      }
      Map<String, GHBranch> branches = repo.getBranches();
      Map<String, GHRepository> repositories = organization.getRepositories();
      Iterator entries1 = repositories.entrySet().iterator();
      while (entries.hasNext()) {
        Map.Entry entry = (Map.Entry) entries.next();
        String key = (String) entry.getKey();
        GHRepository value = (GHRepository) entry.getValue();
        Map<String, GHBranch> users = value.getBranches();
        System.out.println("Key = " + key + ", Value = " + value);
        System.out.println("Developers/Team Members:" + users.toString());
      }
      System.out.println("Details: " + repo.toString());
      System.out.println("organization: " + organization.toString());
      System.out.println("branches: " + branches.toString());
      System.out.println("Teams: " + teams.toString());
      System.out.println("Repositories: " + repositories.toString());
    } catch (IOException e) {
      System.out.println("Exception: " + e.getCause());
      System.out.println("Exception: " + e.getMessage());
      e.printStackTrace();
    }

  }
}
