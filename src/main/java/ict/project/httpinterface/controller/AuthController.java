package ict.project.httpinterface.controller;

import lombok.RequiredArgsConstructor;
import ict.project.httpinterface.service.OAuth2RestService;
import ict.project.model.User.OauthType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class AuthController {
    private final OAuth2RestService oAuth2RestService;

    @GetMapping("/auth/{type}")
    public boolean authenticate(@PathVariable(name = "type") String oauthType) {
        System.out.println("oauthType = " + oauthType);
        return oAuth2RestService.authenticate(OauthType.ofType(oauthType));
    }
}
