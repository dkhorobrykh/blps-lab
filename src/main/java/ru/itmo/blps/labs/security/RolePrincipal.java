package ru.itmo.blps.labs.security;

import java.security.Principal;
import java.util.Objects;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RolePrincipal implements Principal {
    private final String roleName;

    @Override
    public String getName() {
        return roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolePrincipal that = (RolePrincipal) o;
        return Objects.equals(roleName, that.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleName);
    }
}
