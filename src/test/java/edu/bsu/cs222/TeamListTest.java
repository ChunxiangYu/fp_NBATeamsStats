package edu.bsu.cs222;
import edu.bsu.cs222.Model.TeamList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import net.minidev.json.JSONArray;

import java.util.Collections;
import java.util.List;

public class TeamListTest {
    @Test
    public void testCreateFullListOfTeams() throws Exception {
        List list = TeamList.getNewListOfTeams().createFullListOfTeams();
        Assertions.assertNotNull(list);
    }
    @Test
    public void testGetTeamList() throws Exception {
        TeamList listOfTeams = TeamList.getNewListOfTeams();
        JSONArray teams = listOfTeams.getTeamList();
        Assertions.assertNotNull(teams);
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
