package com.example.usermanagement.controller;

import com.example.usermanagement.dto.request.GenericServiceResult;
import com.example.usermanagement.dto.request.LoginRequestDTO;
import com.example.usermanagement.dto.response.LoginResponseDTO;
import com.example.usermanagement.entity.User;
import com.example.usermanagement.enums.ErrorType;
import com.example.usermanagement.enums.ResponseStatus;
import com.example.usermanagement.exception.GenericServiceException;
import com.example.usermanagement.jwt.JwtTokenUtil;
import com.example.usermanagement.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtTokenUtil jwtUtil;

    private final UserService userServiceService;

    private final AuthenticationManager authManager;

    public AuthController(JwtTokenUtil jwtUtil, UserService userServiceService, AuthenticationManager authManager) {
        this.jwtUtil = jwtUtil;
        this.userServiceService = userServiceService;
        this.authManager = authManager;
    }

    @PostMapping("/login")
    public ResponseEntity<GenericServiceResult> createAuthenticationToken(@RequestBody LoginRequestDTO authenticationRequest) {

        try {
            authManager.authenticate
                    (new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
                    );
        } catch (RuntimeException e) {
            throw new GenericServiceException(ErrorType.BAD_CREDENTIAL);
        }


        final User userDetails = (User) userServiceService.loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);
        return new ResponseEntity<>(new GenericServiceResult(ResponseStatus.SUCCESS, ResponseStatus.SUCCESS.getResultCode(), ResponseStatus.SUCCESS.toString(), new LoginResponseDTO(userDetails.getId(), userDetails.getName(), userDetails.getSurname(), userDetails.getUsername(), userDetails.getRole(), jwt )), HttpStatus.OK);

    }

}