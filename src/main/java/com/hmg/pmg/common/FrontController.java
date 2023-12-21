package com.hmg.pmg.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {

  @GetMapping("/auth")
  public String getLoginPage() {
    return "/login";
  }

  @GetMapping("/member/signUp")
  public String getSignUpPage() {
    return "/signUp";
  }
}
