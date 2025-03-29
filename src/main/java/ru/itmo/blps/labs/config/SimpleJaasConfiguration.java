package ru.itmo.blps.labs.config;

import java.util.Map;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itmo.blps.labs.security.CustomJaasLoginModule;
import ru.itmo.blps.labs.service.UserService;

@RequiredArgsConstructor
@org.springframework.context.annotation.Configuration
public class SimpleJaasConfiguration extends Configuration {

    private final UserService userService;

    @Override
    public AppConfigurationEntry[] getAppConfigurationEntry(String name) {
        return new AppConfigurationEntry[]{
            new AppConfigurationEntry(
                CustomJaasLoginModule.class.getName(),
                AppConfigurationEntry.LoginModuleControlFlag.REQUIRED,
                Map.of("userService", userService)
            )
        };
    }
}
