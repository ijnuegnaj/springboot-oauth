package ict.project.httpinterface.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ict.project.model.User.OauthType;

@Getter
@RequiredArgsConstructor
public class OAuth2UserInfo {
    private final OauthType oauthType;
    private final String oauthId;
    private final String name;
}
