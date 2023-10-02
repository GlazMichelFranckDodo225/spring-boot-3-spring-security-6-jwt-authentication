package com.dgmf.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Set;

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
}
