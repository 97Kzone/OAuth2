package com.vvs.peekpick.global.oauth.common.converters;

import com.vvs.peekpick.member.dto.User;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;

/**
 * OAuth 2.0 인증에 필요한 정보 관리 class
 * ClientRegistration, OAuth2User -> Spring 제공
 * User -> 소셜 통합 DTO ( 사용자 정의 )
 */
public class ProviderUserRequest {
    private ClientRegistration clientRegistration;
    private OAuth2User oAuth2User;
    private User user;

    public ProviderUserRequest(ClientRegistration clientRegistration, OAuth2User oAuth2User, User user) {
        this.clientRegistration = clientRegistration;
        this.oAuth2User = oAuth2User;
        this.user = user;
    }

    public ProviderUserRequest(ClientRegistration clientRegistration, OAuth2User oAuth2User) {
        this(clientRegistration, oAuth2User, null);
    }

    public ProviderUserRequest(User user) {
        this(null, null, user);
    }

    public ClientRegistration getClientRegistration() {
        return clientRegistration;
    }

    public OAuth2User getOAuth2User() {
        return oAuth2User;
    }

    public User getUser() {
        return user;
    }
}