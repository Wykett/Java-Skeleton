package backend.foritech.backend.repositories;

import backend.foritech.backend.entities.PersonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<PersonEntity, Long> {

    PersonEntity findByPersonEmailIgnoreCase(String personEmail);
}
