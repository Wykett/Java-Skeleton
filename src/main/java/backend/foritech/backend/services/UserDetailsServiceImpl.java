package backend.foritech.backend.services;

import backend.foritech.backend.entities.PersonEntity;
import backend.foritech.backend.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private PersonRepository personRepo;

	@Override
	@Transactional
	public UserDetailsImpl loadUserByUsername(String username) {
		PersonEntity person = null;
		person = personRepo.findByPersonEmailIgnoreCase(username);

		return UserDetailsImpl.build(person);
	}
}