package org.san.oauth2practice.httpinterface.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.san.oauth2practice.model.User.OauthType;

@Getter
@RequiredArgsConstructor
public class OAuth2UserInfo {
    private final OauthType oauthType;
    private final String oauthId;
    private final String name;
}
