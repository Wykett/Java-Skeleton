package backend.foritech.backend.services;

import backend.foritech.backend.beans.PersonBean;
import backend.foritech.backend.entities.PersonEntity;
import org.springframework.stereotype.Service;

@Service
public interface PersonService {

    PersonEntity findByUserEmail(String userEmail);

    PersonEntity createPerson(PersonEntity person);

    PersonEntity updatePerson(PersonEntity personEntity);
}
