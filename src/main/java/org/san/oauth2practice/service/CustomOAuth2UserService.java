package org.san.oauth2practice.service;

import java.util.List;
import java.util.Map;

import org.san.oauth2practice.model.User.User;
import org.san.oauth2practice.repository.InMemoryUserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final InMemoryUserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);
        log.info("oAuth2User: {}", oauth2User);
        final Map<String, Object> attributes = oauth2User.getAttributes();
        final String oauthId = String.valueOf(attributes.get("id"));
        final String oauthType = userRequest.getClientRegistration().getRegistrationId();

        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_GUEST"));
        final User findUser = userRepository.findByOAuthIdAndOAuthType(oauthId, oauthType);
        if (findUser != null) {
            if (!findUser.getOauthId().equals(oauthId) && !findUser.getOauthType().equals(oauthType)) {
                throw new OAuth2AuthenticationException("oauth information not matched!");
            }
            // 로그인 ROLE_USER
            authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
            return new DefaultOAuth2User(authorities, oauth2User.getAttributes(), "id");
        }
        // ROLE_GUEST
        return new DefaultOAuth2User(authorities, oauth2User.getAttributes(), "id");
    }
}
