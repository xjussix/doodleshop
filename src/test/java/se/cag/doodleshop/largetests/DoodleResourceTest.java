/*
 * Created by Daniel Marell 14-06-03 16:51
 */
package se.cag.doodleshop.largetests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import se.cag.doodleshop.Application;
import se.cag.doodleshop.model.DoodleMessage;
import se.cag.doodleshop.testcategories.LargeTest;
import se.cag.doodleshop.util.Environment;

import java.sql.Timestamp;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@Category(LargeTest.class)
public class DoodleResourceTest {
    @BeforeClass
    public static void setUpClass() {
        Environment.setEnvironment(Environment.IT);
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
    public void test1() throws Exception {
        System.out.println("*** executed DoodleResourceTest ***");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        RestTemplate template = new RestTemplate();

        HttpEntity<String> requestEntity = new HttpEntity<String>(
                createJson(createDoodleMessage("message")), headers);

        ResponseEntity<Long> entity = template.postForEntity(
                "http://localhost:9200/doodles",
                requestEntity, Long.class);


        assertEquals(HttpStatus.OK, entity.getStatusCode());
        Long id = entity.getBody();
        assertEquals(1L, id.longValue());
    }

    private DoodleMessage createDoodleMessage(String message) {
        DoodleMessage m = new DoodleMessage();
        m.setId(1L);
        m.setAuthor("author");
        m.setMessage(message);
        m.setCreated(new Timestamp(2L));
        return m;
    }

    private String createJson(Object obj) {
        ObjectMapper objectMapper;
        objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Failed to convert object to json", e);
        }
    }
}

