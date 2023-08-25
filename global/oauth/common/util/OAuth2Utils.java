package com.vvs.peekpick.global.oauth.common.util;

import com.vvs.peekpick.global.oauth.model.Attributes;
import com.vvs.peekpick.global.oauth.model.PrincipalUser;
import com.vvs.peekpick.global.oauth.common.enums.OAuth2Config;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

/**
 * OAuth Attribute 통합 관리하기 위한 Util
 * Attribute : 사용자의 정보, 세부 사항 (name, email, etc..)
 */
public class OAuth2Utils {

    public static Attributes getMainAttributes(OAuth2User oAuth2User) {

        return Attributes.builder()
                .mainAttributes(oAuth2User.getAttributes())
                .build();
    }

    public static Attributes getSubAttributes(OAuth2User oAuth2User, String subAttributesKey) {

        Map<String, Object> subAttributes = (Map<String, Object>) oAuth2User.getAttributes().get(subAttributesKey);
        return Attributes.builder()
                         .subAttributes(subAttributes)
                         .build();
    }

    public static Attributes getOtherAttributes(OAuth2User oAuth2User, String subAttributesKey, String otherAttributesKey) {

        Map<String, Object> subAttributes = (Map<String, Object>) oAuth2User.getAttributes().get(subAttributesKey);
        Map<String, Object> otherAttributes = (Map<String, Object>) subAttributes.get(otherAttributesKey);

        return Attributes.builder()
                         .subAttributes(subAttributes)
                         .otherAttributes(otherAttributes)
                         .build();
    }
}