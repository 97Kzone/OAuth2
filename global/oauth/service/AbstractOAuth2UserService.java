package com.vvs.peekpick.global.oauth.service;

import com.vvs.peekpick.global.oauth.common.converters.ProviderUserConverter;
import com.vvs.peekpick.global.oauth.common.converters.ProviderUserRequest;
import com.vvs.peekpick.global.oauth.model.ProviderUser;
import com.vvs.peekpick.member.repository.MemberRepository;
import com.vvs.peekpick.member.service.MemberService;
import com.vvs.peekpick.response.ResponseService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Getter
@Service
@RequiredArgsConstructor
public abstract class AbstractOAuth2UserService {

    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final ProviderUserConverter<ProviderUserRequest, ProviderUser> providerUserConverter;
    private final ResponseService responseService;

    public ProviderUser providerUser(ProviderUserRequest providerUserRequest){
        return providerUserConverter.converter(providerUserRequest);
    }
}
