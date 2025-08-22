package ict.project.oauth2client.service.strategy;

import ict.project.model.User.OauthType;
import ict.project.oauth2client.service.OAuth2UserInfo;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface OAuth2Strategy {
    OauthType getOAuth2ProviderType();

    OAuth2UserInfo getUserInfo(OAuth2User user);

    // boolean unlinkOAuth2Account();

    default void isOauthIdExist(String oauthId) {
        if (null == oauthId) {
            throw new OAuth2AuthenticationException("oauthId does not exist");
        }
    }
}
