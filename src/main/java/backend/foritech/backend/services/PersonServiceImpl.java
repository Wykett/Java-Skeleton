package backend.foritech.backend.services;

import backend.foritech.backend.beans.PersonBean;
import backend.foritech.backend.entities.PersonEntity;
import backend.foritech.backend.repositories.PersonRepository;
import backend.foritech.backend.utils.ModelMapperUtil;
import com.stripe.model.Person;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    private static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Autowired
    PersonRepository repo;

    @Override
    public PersonEntity findByUserEmail(String userEmail) {
        return repo.findByPersonEmailIgnoreCase(userEmail);
    }

    @Override
    public PersonEntity createPerson(PersonEntity personEntity) {
        if (personEntity.getPersonEmail() != null && !personEntity.getPersonEmail().isEmpty() && personEntity.getPersonEmail().contains("@")) {
            logger.info("User " + personEntity + " has been created.");
            return repo.save(personEntity);
        }
        return repo.save(personEntity);
    }

    @Override
    public PersonEntity updatePerson(PersonEntity personEntity) {
        PersonEntity personToUpdate = findByUserEmail(personEntity.getPersonEmail());
        personEntity.setPersonId(personToUpdate.getPersonId());

        return repo.save(personEntity);
    }


}
