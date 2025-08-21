package org.san.oauth2practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccessController {

    @GetMapping("/access-user")
    public String accessUser() {
        return "✅ 로그인 성공! USER 전용 페이지입니다.";
    }

    @GetMapping("/access-guest")
    public String accessGuest() {
        return "👋 게스트 접근 페이지입니다.";
    }
}



