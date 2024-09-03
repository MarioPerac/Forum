package org.unibl.etf.sni.forum.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.unibl.etf.sni.forum.services.TwoFactorAuthenticationService;

public class TwoFactorAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final TwoFactorAuthenticationService twoFactorAuthenticationService;

    public TwoFactorAuthenticationFilter(AuthenticationManager authenticationManager, TwoFactorAuthenticationService twoFactorAuthenticationService) {
        super.setAuthenticationManager(authenticationManager);
        this.twoFactorAuthenticationService = twoFactorAuthenticationService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        Authentication authentication = super.attemptAuthentication(request, response);

        String username = request.getParameter("username");
        String code = request.getParameter("code");

        if (!twoFactorAuthenticationService.verifyCode(username, code)) {
            throw new AuthenticationException("Invalid 2FA code") {};
        }

        return authentication;
    }
}
