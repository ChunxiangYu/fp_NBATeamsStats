import com.mashape.unirest.http.exceptions.UnirestException;
import edu.bsu.CS222.HttpRequest;
import org.junit.Test;

public class TeamStatsRequestTest {
    @Test
    public void testHttpRequest() throws Exception {
        HttpRequest request = new HttpRequest();
        try {
            request.sendRequest();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }
}
