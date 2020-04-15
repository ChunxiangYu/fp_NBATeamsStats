package edu.bsu.cs222.Model;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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



    public static InputStream getTeamList() throws Exception {
        URL url = new URL("http://data.nba.net/data/10s/prod/v1/2018/teams.json");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        return con.getInputStream();
    }


}
