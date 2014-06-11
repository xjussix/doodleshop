/*
 * Created by Daniel Marell 14-03-02 12:02
 */
package se.caglabs.doodleshop.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import se.caglabs.doodleshop.model.DoodleMessage;
import se.caglabs.doodleshop.repository.DoodleMessageRepository;
import se.caglabs.doodleshop.util.BuildInfo;

import java.sql.Timestamp;
import java.util.List;

@Controller
public class DoodleResource {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DoodleMessageRepository repository;

    @RequestMapping(value = "/doodles", method = RequestMethod.POST)
    @ResponseBody
    public long saveDoodleMessage(@RequestBody DoodleMessage doodle) {
        log.info("saveDoodleMessage:" + doodle);
        doodle.setCreated(new Timestamp(System.currentTimeMillis()));
        repository.save(doodle);
        return doodle.getId();
    }

    @RequestMapping(value = "/doodles/{id}", method = RequestMethod.GET)
    @ResponseBody
    public DoodleMessage getDoodleMessageById(@PathVariable(value = "id") long id) {
        return repository.findById(id);
    }

    @RequestMapping(value = "/doodles", method = RequestMethod.GET)
    @ResponseBody
    public List<DoodleMessage> getDoodleMessageBySender(
            @RequestParam(value = "author") String author,
            @RequestParam(value = "latestSamples", required = false) Integer latestSamples) {
        if (latestSamples == null) {
            latestSamples = 10;
        }
        return repository.findByAuthorOrderByCreatedAsc(author, new PageRequest(0, latestSamples));
    }

    @RequestMapping(value = "/version", method = RequestMethod.GET)
    @ResponseBody
    public String getVersion() {
        return BuildInfo.getApplicationVersion();
    }

}