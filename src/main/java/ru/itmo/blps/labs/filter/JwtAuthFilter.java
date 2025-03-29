package ru.itmo.blps.labs.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.jaas.JaasAuthenticationProvider;
import org.springframework.security.authentication.jaas.JaasAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itmo.blps.labs.config.SimpleJaasConfiguration;
import ru.itmo.blps.labs.domain.CustomUserDetails;
import ru.itmo.blps.labs.domain.Role;
import ru.itmo.blps.labs.security.CustomJaasLoginModule;
import ru.itmo.blps.labs.security.RolePrincipal;
import ru.itmo.blps.labs.security.UserPrincipal;
import ru.itmo.blps.labs.service.JwtProvider;
import ru.itmo.blps.labs.service.JwtUtils;
import ru.itmo.blps.labs.service.UserService;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION = "Authorization";

    private final JwtProvider jwtProvider;

    private final UserService userService;
    private final AuthenticationProvider jaasAuthenticationProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

        final String token = getTokenFromRequest(request);

        if (token != null && jwtProvider.validateAccessToken(token)) {
            final Claims claims = jwtProvider.getAccessClaims(token);
            Long userId = Long.valueOf(claims.get("userId").toString());

            try {
                CallbackHandler callbackHandler = callbacks -> {
                    for (var callback : callbacks) {
                        if (callback instanceof NameCallback nameCallback) {
                            nameCallback.setName(userId.toString());
                        } else {
                            throw new UnsupportedCallbackException(callback, "Unknown callback");
                        }
                    }
                };

                Configuration config = new Configuration() {
                    @Override
                    public AppConfigurationEntry[] getAppConfigurationEntry(String name) {
                        Map<String, Object> options = new HashMap<>();
                        options.put("userService", userService);

                        return new AppConfigurationEntry[]{
                            new AppConfigurationEntry(
                                CustomJaasLoginModule.class.getName(),
                                AppConfigurationEntry.LoginModuleControlFlag.REQUIRED,
                                options
                            )
                        };
                    }
                };

                var loginContext = new LoginContext("JAAS_AUTH", null, callbackHandler, config);
                loginContext.login();

                var authorities = userService.getById(userId)
                    .getRoles()
                    .stream()
                    .flatMap(role -> {
                        Set<GrantedAuthority> roleAuthorities = new HashSet<>();
                        roleAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));

                        Set<GrantedAuthority> permissionAuthorities = role.getPrivileges()
                            .stream()
                            .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                            .collect(Collectors.toSet());

                        roleAuthorities.addAll(permissionAuthorities);

                        return roleAuthorities.stream();
                    })
                    .toList();

                var authToken = new JaasAuthenticationToken(
                    userId,
                    null,
                    authorities,
                    loginContext
                );

                var authenticated = jaasAuthenticationProvider.authenticate(authToken);
                SecurityContextHolder.getContext().setAuthentication(authenticated);

            } catch (LoginException ex) {
                log.error("Login failed", ex);
                throw new RuntimeException("Login failed", ex);
            }
        }

        filterChain.doFilter(request, response);
    }


    private String getTokenFromRequest(HttpServletRequest request) {
        final String bearer = request.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }

}

