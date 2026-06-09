package com.cms.helper;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Helper {

    public static String getEmailOfLoggedInUser(Authentication authentication) {
        String username = "";

        if(authentication instanceof OAuth2AuthenticationToken) {
            var oAuth2AuthenticationToken = (OAuth2AuthenticationToken)authentication;
            var clientId = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();

            var oAuth2User = (OAuth2User)authentication.getPrincipal();

            if(clientId.equalsIgnoreCase("google")) {
                username = oAuth2User.getAttribute("email").toString();
            }

        } else {
            return authentication.getName();
        }

        return username;
    }

}
