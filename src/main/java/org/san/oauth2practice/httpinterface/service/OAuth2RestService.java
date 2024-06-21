package org.san.oauth2practice.httpinterface.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.san.oauth2practice.model.User.OauthType;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class OAuth2RestService {
    private final OAuth2RestStrategyComposite oauth2RestStrategyComposite;

    public boolean authenticate(OauthType oauthType) {
        log.info("oauthType: {}", oauthType);
        final OAuth2UserInfo userInfo = oauth2RestStrategyComposite.getOAuth2Strategy(oauthType).getUserInfo();

        return Objects.nonNull(userInfo);
    }
}
