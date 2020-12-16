package backend.foritech.backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/apiPublic/simpleApis")
public class SimpleController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ResponseEntity<?> uploadFile(@RequestParam(value = "userEmail", required = true) String userEmail) {
        return ResponseEntity.ok(userEmail);
    }

}
