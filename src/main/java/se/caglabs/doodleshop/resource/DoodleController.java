/*
 * Created by Daniel Marell 14-03-02 12:02
 */
package se.caglabs.doodleshop.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import se.caglabs.doodleshop.model.DoodleMessage;
import se.caglabs.doodleshop.repository.DoodleMessageRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Transactional
public class DoodleController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DoodleMessageRepository repository;

    @RequestMapping(value = "/doodles", method = RequestMethod.POST)
    public long storeDoodleMessage(@RequestBody DoodleMessage doodle) {
        logger.info("storeDoodleMessage:" + doodle);
        doodle.setCreated(new Date(System.currentTimeMillis()));
        repository.save(doodle);
        return doodle.getId();
    }

    @RequestMapping(value = "/doodles/{id}", method = RequestMethod.GET)
    public DoodleMessage getDoodleMessageById(@PathVariable(value = "id") long id) {
        return repository.findById(id);
    }

    @RequestMapping(value = "/doodles", method = RequestMethod.GET)
    public List<DoodleMessage> getDoodleMessageBySender(
            @RequestParam(value = "author", required = false) String author,
            @RequestParam(value = "latestSamples", required = false) Integer latestSamples) {
        if (latestSamples == null) {
            latestSamples = 10;
        }
        if (author != null) {
            return repository.findByAuthorOrderByCreatedAsc(author, new PageRequest(0, latestSamples));
        }
        List<DoodleMessage> result = new ArrayList<DoodleMessage>();
        for (DoodleMessage m : repository.findAll()) {
            result.add(m);
        }
        return result;
    }
}