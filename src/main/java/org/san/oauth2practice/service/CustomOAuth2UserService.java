package org.san.oauth2practice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.san.oauth2practice.model.User.OauthType;
import org.san.oauth2practice.model.User.User;
import org.san.oauth2practice.repository.InMemoryUserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final InMemoryUserRepository userRepository;
    private final OAuth2StrategyComposite oauth2StrategyComposite;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);

        OAuth2UserInfo oauth2UserInfo = oauth2StrategyComposite
                .getOAuth2Strategy(getSocialProvider(userRequest))
                .getUserInfo(oauth2User);
        List<SimpleGrantedAuthority> authorities = getAuthorities(oauth2UserInfo);
        log.info("oauth2USerINfo: {} {}", oauth2UserInfo.getOauthId(), oauth2UserInfo.getName());

        return new DefaultOAuth2User(authorities, oauth2User.getAttributes(), "id");
    }

    private OauthType getSocialProvider(OAuth2UserRequest userRequest) {
        return OauthType.ofType(userRequest.getClientRegistration().getRegistrationId());
    }

    private List<SimpleGrantedAuthority> getAuthorities(OAuth2UserInfo userInfo) {
        final User findUser = userRepository.findByOAuthIdAndOAuthType(userInfo.getOauthId(), userInfo.getOauthType());
        if (findUser != null) {
            if (!findUser.getName().equals(userInfo.getName())) {
                throw new OAuth2AuthenticationException("oauth information not matched!");
            }
            // 로그인 ROLE_USER
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return List.of(new SimpleGrantedAuthority("ROLE_GUEST"));
    }
}
