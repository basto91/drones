package com.musala.drone.drone.service;

import com.musala.drone.drone.configuration.security.jwt.JwtProvider;
import com.musala.drone.drone.service.dto.AuthenticationRequest;
import com.musala.drone.drone.service.dto.AuthenticationResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager  authenticationManager;
    private final JwtProvider jwtProvider;

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        String jwt = jwtProvider.generateJwtToken(authentication);
        return AuthenticationResponse.builder().jwt(jwt).type("Bearer").build();
    }
}
