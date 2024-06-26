package com.sanij.USRNbackend.controllers;

import com.sanij.USRNbackend.models.Account;
import com.sanij.USRNbackend.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RegisterController {

    @Autowired
    public AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerNewUser(@RequestBody Account account) {

        String accEmail = account.getEmail();

        //check if alredy registered or not.
        if(accountService.findByEmail(accEmail).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Email is already registered");
        }

        // if not registered, encode password.
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(account.getPassword());
        account.setPassword(encodedPassword);



        Account createdAcc = accountService.save(account);
        return ResponseEntity.ok(createdAcc);
    }
}
