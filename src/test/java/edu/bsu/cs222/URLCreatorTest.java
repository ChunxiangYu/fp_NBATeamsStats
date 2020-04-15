package edu.bsu.cs222;


import edu.bsu.cs222.Model.URLCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

public class URLCreatorTest {

    @Test
    public void testGetTeamStatsUrlStream() throws Exception {
        InputStream url = URLCreator.getTeamStats("current");
        Assertions.assertNotNull(url);
    }
    @Test
    public void testGetTeamStatsUrl() throws Exception {
        InputStream url = URLCreator.getTeamStats("current");
        boolean b = ("http://data.nba.net/data/10s/prod/v1/current/teams.json".compareTo(String.valueOf(url))==0)?false:true;
        Assertions.assertTrue(b);
    }

    @Test
    public void testGetTeamListUrlStream() throws Exception {
        InputStream url = URLCreator.getTeamList();
        Assertions.assertNotNull(url);
    }
    @Test
    public void testGetTeamListUrl() throws Exception {
        InputStream url = URLCreator.getTeamList();
        boolean b = ("http://data.nba.net/data/10s/prod/v1/2018/teams.json".compareTo(String.valueOf(url))==0)?false:true;
        Assertions.assertTrue(b);
    }
}
