package ru.itmo.blps.labs.security;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.TextInputCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itmo.blps.labs.domain.Role;
import ru.itmo.blps.labs.domain.User;
import ru.itmo.blps.labs.exception.CustomException;
import ru.itmo.blps.labs.exception.ExceptionEnum;
import ru.itmo.blps.labs.service.AuthService;
import ru.itmo.blps.labs.service.UserService;

@Slf4j
@RequiredArgsConstructor
public class CustomJaasLoginModule implements LoginModule {
    private Subject subject;
    private CallbackHandler callbackHandler;
    private UserService userService;
    private Long userId;
    private Set<Principal> principals = new HashSet<>();

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
        this.userService = (UserService) options.get("userService");
    }

    @Override
    public boolean login() {
        var login = new NameCallback("login: ");

        try {
            callbackHandler.handle(new Callback[]{login});
        } catch (Exception e) {
            throw new CustomException(ExceptionEnum.SERVER_ERROR);
        }

        String loginText = login.getName();
        userId = Long.parseLong(loginText);

        return true;
    }

    @Override
    public boolean commit() {

        subject.getPrincipals().add(new UserPrincipal(userId.toString()));

        return true;
    }

    @Override
    public boolean abort() {
        return false;
    }

    @Override
    public boolean logout() {
        subject.getPrincipals().removeAll(principals);
        principals.clear();
        return true;
    }
}
