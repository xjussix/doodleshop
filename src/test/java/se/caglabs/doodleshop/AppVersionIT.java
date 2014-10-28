/*
 * Created by Daniel Marell 14-09-26 07:37
 */
package se.caglabs.doodleshop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import se.caglabs.doodleshop.util.BuildInfo;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class AppVersionIT {
    @Test
    public void shouldGetAppVersion() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        RestTemplate template = new RestTemplate();

        ResponseEntity<String> entity = template.getForEntity(
                "http://localhost:8080/appversion",
                String.class);

        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals(BuildInfo.getAppVersion(), entity.getBody());
    }
}
