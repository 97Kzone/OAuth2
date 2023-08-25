package com.vvs.peekpick.global.oauth.service;

import com.vvs.peekpick.global.oauth.common.converters.ProviderUserConverter;
import com.vvs.peekpick.global.oauth.common.converters.ProviderUserRequest;
import com.vvs.peekpick.global.oauth.model.PrincipalUser;
import com.vvs.peekpick.global.oauth.model.ProviderUser;
import com.vvs.peekpick.member.repository.MemberRepository;
import com.vvs.peekpick.member.service.MemberService;
import com.vvs.peekpick.response.ResponseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

/**
 AbstractOAuth2UserService 를 상속받고
 OAuth2UserService 를 구현한다

 OAuth2UserService, OAuth2UserRequest, OAuth2User -> Spring 제공
 loadUser -> Spring 호출
*/
@Slf4j
@Service
public class CustomOAuth2UserService extends AbstractOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    public CustomOAuth2UserService(MemberRepository memberRepository, MemberService memberService,
                                   ProviderUserConverter<ProviderUserRequest, ProviderUser> providerUserConverter,
                                   ResponseService responseService) {
        super(memberRepository, memberService, providerUserConverter, responseService);
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

        ProviderUserRequest providerUserRequest = new ProviderUserRequest(clientRegistration, oAuth2User, null);
        ProviderUser providerUser = providerUser(providerUserRequest);

        return new PrincipalUser(providerUser);
    }
}
