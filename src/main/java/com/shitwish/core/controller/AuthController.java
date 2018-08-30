package com.shitwish.core.controller;

import com.auth0.AuthenticationController;
import com.auth0.IdentityVerificationException;
import com.auth0.Tokens;
import com.shitwish.core.security.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AuthController {

    private final AuthenticationController controller;
    private final String userInfoAudience;
    private final String userInfoScope;
    private final String callback;


    @Autowired
    public AuthController(AppConfig config) {
        controller = AuthenticationController.newBuilder(config.getDomain(), config.getClientId(), config.getClientSecret())
                .build();
        userInfoAudience = String.format("https://%s/userinfo", config.getDomain());
        userInfoScope = "openid%20profile%20email";
        callback = String.format("https://%s/callback", config.getDomain());
    }

    public Tokens handle(HttpServletRequest request) throws IdentityVerificationException {
        return controller.handle(request);
    }

    public String buildAuthorizeUrl(HttpServletRequest request, String redirectUri) {
        return controller
                .buildAuthorizeUrl(request, redirectUri)
                .withAudience(userInfoAudience)
                .withScope(userInfoScope)
                .withParameter("response_type", "id_token%20token" )
                .withParameter("redirect_uri", callback)
                .build();
    }


    /*https://teammate.eu.auth0.com/authorize?
    audience=YOUR_API_AUDIENCE&
    scope=openid%20profile%20email%20read:contacts&
    response_type=id_token%20token&
    client_id=LiLXIROa1xWfMx-CsgWdOnYpTt_y_97u&
    redirect_uri=https://YOUR_APP/callback&
    nonce=YOUR_CRYPTOGRAPHIC_NONCE
            state=YOUR_OPAQUE_VALUE*/

}