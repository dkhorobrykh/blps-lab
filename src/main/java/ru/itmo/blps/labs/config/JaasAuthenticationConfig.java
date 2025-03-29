package ru.itmo.blps.labs.config;

import java.util.HashMap;
import java.util.Map;
import javax.security.auth.login.AppConfigurationEntry;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.authentication.jaas.DefaultJaasAuthenticationProvider;
import org.springframework.security.authentication.jaas.JaasAuthenticationProvider;
import ru.itmo.blps.labs.security.CustomAuthorityGranter;
import ru.itmo.blps.labs.security.CustomJaasLoginModule;
import ru.itmo.blps.labs.service.UserService;

@Configuration
@RequiredArgsConstructor
public class JaasAuthenticationConfig {
    private final UserService userService;

    @Bean
    public AuthenticationProvider jaasAuthenticationProvider() {
        javax.security.auth.login.Configuration config = new javax.security.auth.login.Configuration() {
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

        var provider = new DefaultJaasAuthenticationProvider();
        provider.setAuthorityGranters(new AuthorityGranter[]{new CustomAuthorityGranter(userService)});
        provider.setConfiguration(config);
        return provider;
    }
}
