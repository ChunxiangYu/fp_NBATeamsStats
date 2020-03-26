package edu.bsu.CS222;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest {
    public static InputStream getTeamStats() throws Exception {
            URL url = new URL("http://data.nba.net/data/10s/prod/v1/current/standings_all_no_sort_keys.json");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            return con.getInputStream();
    }
    public static InputStream getTeamList() throws Exception {
        URL url = new URL("http://data.nba.net/data/10s/prod/v1/2018/teams.json");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        return con.getInputStream();
    }
}