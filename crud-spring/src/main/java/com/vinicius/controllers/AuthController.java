package com.vinicius.controllers;

import com.vinicius.dto.request.CreateAuthDTO;
import com.vinicius.dto.request.CreateRegisterDTO;
import com.vinicius.entities.Users;
import com.vinicius.repositories.UsersRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private AuthenticationManager authenticationManager;

    private final UsersRepository usersRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid CreateAuthDTO createAuthDTO){
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(createAuthDTO.email(), createAuthDTO.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid CreateRegisterDTO createRegisterDTO){
        if(this.usersRepository.findByEmail(createRegisterDTO.email()) != null)
            return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(createRegisterDTO.password());
        Users newUser = new Users(createRegisterDTO.email(), encryptedPassword, createRegisterDTO.role());

        this.usersRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
