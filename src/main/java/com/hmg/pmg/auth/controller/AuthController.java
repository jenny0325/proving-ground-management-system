package com.hmg.pmg.auth.controller;

import static com.hmg.pmg.common.response.SuccessMessage.LOGIN_SUCCESS;

import com.hmg.pmg.auth.dto.AuthLoginDto;
import com.hmg.pmg.auth.dto.TokenRequestDto;
import com.hmg.pmg.auth.service.AuthService;
import com.hmg.pmg.common.response.CustomResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<Object> signIn(@RequestBody @Valid AuthLoginDto authLoginDto) {
    CustomResponse<Object> response = CustomResponse
        .success(authService.login(authLoginDto), LOGIN_SUCCESS);

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @PostMapping("/logout")
  public ResponseEntity<?> logout(@RequestBody TokenRequestDto tokenRequestDto) {
    authService.logout(tokenRequestDto);
    return ResponseEntity.ok().build();
  }
}