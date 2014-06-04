/*
 * Created by Daniel Marell 14-02-22 13:50
 */
package se.cag.doodleshop.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import se.cag.doodleshop.model.DoodleMessage;

import java.util.List;

public interface DoodleMessageRepository extends CrudRepository<DoodleMessage, Long> {

    DoodleMessage findById(long id);

    List<DoodleMessage> findByAuthor(String author);

    List<DoodleMessage> findByAuthorOrderByCreatedAsc(String author, Pageable pageable);
}
