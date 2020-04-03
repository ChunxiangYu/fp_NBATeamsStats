package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import java.net.URLEncoder;

public class URLCreator {



    public static URLCreator createUrl() {
        return new URLCreator();
    }

    public static InputStream getTeamStats(String date) throws Exception {
            URL url = new URL("http://data.nba.net/data/10s/prod/v1/"+date+"/standings_all.json");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            return con.getInputStream();
    }
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



    public static InputStream getTeamList() throws Exception {
        URL url = new URL("http://data.nba.net/data/10s/prod/v1/2018/teams.json");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        return con.getInputStream();
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
