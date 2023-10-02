package com.dgmf.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permissions {
    ADMIN_READ("ADMIN::READ"),
    ADMIN_UPDATE("ADMIN::UPDATE"),
    ADMIN_CREATE("ADMIN::CREATE"),
    ADMIN_DELETE("ADMIN::DELETE"),
    MANAGER_READ("MANAGER::READ"),
    MANAGER_UPDATE("MANAGER::UPDATE"),
    MANAGER_CREATE("MANAGER::CREATE"),
    MANAGER_DELETE("MANAGER::DELETE");

    @Getter
    private final String permissions; // Permission name
}
