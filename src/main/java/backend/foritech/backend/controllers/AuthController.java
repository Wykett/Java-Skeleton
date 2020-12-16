package backend.foritech.backend.controllers;

import backend.foritech.backend.beans.JwtResponse;
import backend.foritech.backend.beans.MessageResponse;
import backend.foritech.backend.beans.SignInRequest;
import backend.foritech.backend.beans.SignUpRequest;
import backend.foritech.backend.constants.MessageConstants;
import backend.foritech.backend.entities.PersonEntity;
import backend.foritech.backend.enums.RoleType;
import backend.foritech.backend.security.JwtUtils;
import backend.foritech.backend.services.PersonService;
import backend.foritech.backend.services.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/apiAuth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    public PersonService personService;

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        logger.info("Signup from " + signUpRequest.getPersonEmail());

        if (personService.findByUserEmail(signUpRequest.getPersonEmail()) != null) {
            return ResponseEntity.badRequest().body(new MessageResponse(MessageConstants.USER_EMAIL_EXISTS));
        }

        PersonEntity personEntity = new PersonEntity(signUpRequest.getPersonEmail(), encoder.encode(signUpRequest.getPassword()), RoleType.USER.toString());

        personService.createPerson(personEntity);

        return ResponseEntity.ok(new MessageResponse(MessageConstants.USER_REGISTERED_SUCCESSFULLY));
    }

    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public ResponseEntity<?> login(@Valid @RequestBody SignInRequest signInRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signInRequest.getPersonEmail(), signInRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            if (!userDetails.isVerified()) {
                return ResponseEntity.badRequest().body(new MessageResponse(MessageConstants.USER_NOT_VERIFIED));
            }
            PersonEntity personEntity = personService.findByUserEmail(userDetails.getEmail());
            personEntity.setToken(jwt);
            personService.updatePerson(personEntity);

            return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getEmail(), roles));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(new MessageResponse(MessageConstants.USER_BAD_CREDENTIALS));
        }

    }
}
