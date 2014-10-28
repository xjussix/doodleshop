/*
 * Created by Daniel Marell 14-06-10 07:51
 */
package se.caglabs.doodleshop;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import se.caglabs.doodleshop.model.DoodleMessage;
import se.caglabs.doodleshop.repository.DoodleMessageRepository;
import se.caglabs.doodleshop.resource.DoodleController;
import se.caglabs.doodleshop.util.Config;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DoodleControllerTest {
    @InjectMocks
    DoodleController controller = new DoodleController();

    @Mock
    Config config = mock(Config.class);

    @Mock
    DoodleMessageRepository doodleMessageRepository = mock(DoodleMessageRepository.class);

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetGreetings() throws Exception {
        DoodleMessage lisaGreeting = new DoodleMessage("Lisa", "Okay, time for truth or dare. You go first.");
        DoodleMessage homerGreeting1 = new DoodleMessage("Homer", "Ehh, truth. Ask me anything.");
        DoodleMessage homerGreeting2 = new DoodleMessage("Homer", "D'oh! All right, dare.");

        when(config.getBackgroundColor()).thenReturn("whatever");

        Pageable pageable = new PageRequest(0, 10);
        when(doodleMessageRepository.findByAuthorOrderByCreatedAsc("Lisa", pageable)).thenReturn(Arrays.asList(lisaGreeting));
        when(doodleMessageRepository.findByAuthorOrderByCreatedAsc("Homer", pageable)).thenReturn(Arrays.asList(homerGreeting1, homerGreeting2));
        when(doodleMessageRepository.findAll()).thenReturn(Arrays.asList(lisaGreeting, homerGreeting1, homerGreeting2));

        assertThat(controller.getDoodleMessageBySender("Lisa", null).size(), is(1));
        assertThat(controller.getDoodleMessageBySender("Lisa", null).get(0).getAuthor(), is("Lisa"));
        assertThat(controller.getDoodleMessageBySender("Homer", null).size(), is(2));
        assertThat(controller.getDoodleMessageBySender("Homer", null).get(0).getAuthor(), is("Homer"));
        assertThat(controller.getDoodleMessageBySender("Bart", null).size(), is(0));
    }
}
