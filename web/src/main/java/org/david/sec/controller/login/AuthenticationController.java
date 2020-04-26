package org.david.sec.controller.login;

import lombok.RequiredArgsConstructor;
import org.david.sec.controller.login.request.LoginRequest;
import org.david.sec.dto.GetUserDTO;
import org.david.sec.dto.JwtUserDTO;
import org.david.sec.dto.converter.UserDtoConverter;
import org.david.sec.model.UserEntity;
import org.david.sec.security.jwt.JWTTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JWTTokenProvider tokenProvider;
    private final UserDtoConverter converter;


    @PostMapping("")
    public ResponseEntity<JwtUserDTO> login(@Valid @RequestBody final LoginRequest req) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getUsername(),
                        req.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserEntity user = (UserEntity) authentication.getPrincipal();
        String token = tokenProvider.generateToken(authentication);

        return ResponseEntity.status(HttpStatus.OK).body(converter.convert(user, token));
    }

    @GetMapping("/me")
    public ResponseEntity<GetUserDTO> user(@AuthenticationPrincipal UserEntity user) {
        return ResponseEntity.status(HttpStatus.OK).body(converter.convert(user));
    }

}
