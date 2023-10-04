package com.dgmf.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.dgmf.entity.enums.Permissions.*;

@RequiredArgsConstructor
public enum Role {
    // Has No Permissions
    ROLE_USER(Collections.emptySet()),
    ROLE_ADMIN(
            Set.of(
                    // Permissions
                    ADMIN_READ,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,
                    ADMIN_CREATE,
                    MANAGER_READ,
                    MANAGER_UPDATE,
                    MANAGER_DELETE,
                    MANAGER_CREATE
            )
    ),
    ROLE_MANAGER(
            Set.of(
                    // Permissions
                    MANAGER_READ,
                    MANAGER_UPDATE,
                    MANAGER_DELETE,
                    MANAGER_CREATE
            )
    );

    @Getter
    private final Set<Permissions> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission ->
                        new SimpleGrantedAuthority(permission.name()))
                .toList();

        // Think to Prefix "ROLE_", if necessary ==> "ROLE_" + this.name()
        authorities.add(new SimpleGrantedAuthority(this.name()));

        return authorities;
    }
}
