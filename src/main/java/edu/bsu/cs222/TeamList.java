package edu.bsu.cs222;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TeamList {
    private InputStream in;

    private TeamList() throws Exception {
        in = URLCreator.getTeamList();
    }

    public static TeamList getNewListOfTeams() throws Exception {
        return new TeamList();
    }

    public List<String> createFullListOfTeams() {

        ArrayList<String> teamList = new ArrayList<>();
        JSONArray teamArray = getTeamList();

        for (int i = 0; i < teamArray.size(); i++) {
            teamList.add(teamArray.get(i).toString());
        }
        return teamList;
    }

    @Test
    public void testCreateFullListOfTeams() throws Exception {
        List list = TeamList.getNewListOfTeams().createFullListOfTeams();
        Assertions.assertNotNull(list);
    }



    private JSONArray getTeamList() {
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(in, "UTF-8");
        return JsonPath.read(document, "$..standard[?(@.isNBAFranchise==true)].fullName");
    }

    @Test
    public void testGetTeamList() throws Exception {
        TeamList listOfTeams = TeamList.getNewListOfTeams();
        JSONArray teams = listOfTeams.getTeamList();
        Assertions.assertNotNull(teams);
    }

    public String getTeamId(String teamName) {
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(in, "UTF-8");
        List<String> id = JsonPath.read(document, "$..standard[?(@.fullName=="+ teamName +")].teamId");
        return id.get(0);
    }
    @Test
    public void testGetTeamIdExist() throws Exception {
        TeamList idOfTeams = TeamList.getNewListOfTeams();
        List<String> teams = Collections.singletonList(idOfTeams.getTeamId("Lakers"));
        Assertions.assertNotNull(teams);
    }
    @Test
    public void testGetTeamIdRight() throws Exception {
        TeamList idOfTeams = TeamList.getNewListOfTeams();
        String teamsId = idOfTeams.getTeamId("hawks");
        Assertions.assertEquals("1610612737",teamsId);
    }
}