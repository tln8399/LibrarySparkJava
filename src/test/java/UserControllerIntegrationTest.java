import com.google.gson.Gson;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import spark.Spark;
import spark.utils.IOUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class UserControllerIntegrationTest {

    @BeforeClass
    public static void beforeClass() {
        Main.main(null);
    }

    @AfterClass
    public static void afterClass() {
        Spark.stop();
    }

    @Test
    public void aNewUserShouldBeCreated() {
        TestResponse res = request("POST", "/createUser?fName=tushar&mName=L&lName=Nikam&age=25&gender=M&phone=5853010007&zip=07306");

        Map<String, String> json = res.json();
        assertEquals(200, res.status);
        assertEquals("tushar", json.get("fName"));
        assertEquals("L", json.get("mName"));
        assertEquals("Nikam", json.get("lName"));
        assertEquals(25.0, json.get("age"));
        assertEquals("M", json.get("gender"));
        assertEquals("5853010007", json.get("phone"));
        assertEquals("07306", json.get("zip"));
        //assertEquals("tush@foobar.com", json.get("email"));
        assertNotNull(json.get("id"));

        TestResponse res1 = request("POST", "/AddBook?name=shivaji&checkedByUser=tushar&author=sagar");
        Map<String, String> json1 = res1.json();
        assertEquals(200, res1.status);
        assertEquals("shivaji", json1.get("name"));
        assertNotNull(json1.get("id"));

    }

    private TestResponse request(String method, String path) {
        try {
            URL url = new URL("http://localhost:4567" + path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setDoOutput(true);
            connection.connect();
            String body = IOUtils.toString(connection.getInputStream());
            return new TestResponse(connection.getResponseCode(), body);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Sending request failed: " + e.getMessage());
            return null;
        }
    }

    private static class TestResponse {

        public final String body;
        public final int status;

        public TestResponse(int status, String body) {
            this.status = status;
            this.body = body;
        }

        public Map<String,String> json() {
            return new Gson().fromJson(body, HashMap.class);
        }
    }
}