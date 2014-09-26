/*
 * Created by Daniel Marell 14-09-26 07:37
 */
package se.caglabs.doodleshop.largetests;

import org.junit.*;
import org.junit.experimental.categories.Category;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import se.caglabs.doodleshop.Application;
import se.caglabs.doodleshop.testcategories.LargeTest;
import se.caglabs.doodleshop.util.BuildInfo;
import se.caglabs.doodleshop.util.Environment;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@Category(LargeTest.class)
public class VersionTest {
    @BeforeClass
    public static void setUpClass() {
        Environment.setEnvironment(Environment.AUTOSMALL);
        Application.start();
    }

    @AfterClass
    public static void tearDownClass() {
        Application.stop();
    }


    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testVersion() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        RestTemplate template = new RestTemplate();

        ResponseEntity<String> entity = template.getForEntity(
                "http://localhost:9200/version",
                String.class);

        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals(BuildInfo.getApplicationVersion(), entity.getBody());
    }
}
