package ict.project.httpinterface.provider;

import ict.project.httpinterface.service.OAuth2UserInfo;
import ict.project.model.User.OauthType;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;

public interface RestOAuth2Strategy {
    OauthType getOAuth2Type();

    OAuth2UserInfo getUserInfo();

//    boolean unlinkOAuth2Account(String oauthValue);

    default void isOauthIdExist(String oauthId) {
        if (null == oauthId) {
            throw new OAuth2AuthenticationException("oauthId does not exist");
        }
    }
}
