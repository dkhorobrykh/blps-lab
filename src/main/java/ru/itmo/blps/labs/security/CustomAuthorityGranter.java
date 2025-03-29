package ru.itmo.blps.labs.security;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import ru.itmo.blps.labs.domain.Privilege;
import ru.itmo.blps.labs.service.UserService;

@RequiredArgsConstructor
public class CustomAuthorityGranter implements AuthorityGranter {

    private final UserService userService;

    @Override
    public Set<String> grant(Principal principal) {
        return userService.getById(Long.parseLong(principal.getName())).getRoles().stream().flatMap(role -> {
            Set<String> roleAuthorities = new HashSet<>();
            roleAuthorities.add("ROLE_" + role.getName());

            Set<String> permissionAuthorities =
                role.getPrivileges().stream().map(Privilege::getName).collect(Collectors.toSet());

            roleAuthorities.addAll(permissionAuthorities);

            return roleAuthorities.stream();
        }).collect(Collectors.toSet());
    }
}
