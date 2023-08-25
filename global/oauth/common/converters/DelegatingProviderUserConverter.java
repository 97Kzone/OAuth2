package com.vvs.peekpick.global.oauth.common.converters;

import com.vvs.peekpick.global.oauth.model.ProviderUser;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


/**
 * 모든 소셜 요청을 통합 처리 하기 위한 구현체
 * Converter를 통해 확장성 확보, 일치하는 Provider를 찾아서 요청 처리
 * ProviderUserRequest -> ProviderUser 반환
 */
@Component
public class DelegatingProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser>{

    private List<ProviderUserConverter<ProviderUserRequest, ProviderUser>> converters;

    public DelegatingProviderUserConverter() {
        List<ProviderUserConverter<ProviderUserRequest, ProviderUser>> providerUserConverters =
                Arrays.asList(new UserDetailsProviderUserConverter(),
                              new OAuth2GoogleProviderUserConverter(),
                              new OAuth2NaverProviderUserConverter(),
                              new OAuth2KakaoProviderUserConverter(),
                              new OAuth2KakaoOIdcProviderUserConverter());
        this.converters = Collections.unmodifiableList(new LinkedList<>(providerUserConverters));
    }

    @Override
    public ProviderUser converter(ProviderUserRequest providerUserRequest) {

        Assert.notNull(providerUserRequest, "providerUserRequest cannot be null");

        for(ProviderUserConverter<ProviderUserRequest, ProviderUser> converter : converters) {
            ProviderUser providerUser = converter.converter(providerUserRequest);
            if (providerUser != null) return providerUser;
        }

        return null;
    }
}
