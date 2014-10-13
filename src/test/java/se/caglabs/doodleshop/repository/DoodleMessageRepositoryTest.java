/*
 * Created by Daniel Marell 14-06-10 07:51
 */
package se.caglabs.doodleshop.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import se.caglabs.doodleshop.TestApplicationContext;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestApplicationContext.class)
public class DoodleMessageRepositoryTest {
    @Autowired
    private DoodleMessageRepository repository;

    @Test
    public void testFindById() throws Exception {
        System.out.println("*** executed DoodleMessageRepositoryTest ***");
        assertThat(repository.findById(0), nullValue());
    }
}
