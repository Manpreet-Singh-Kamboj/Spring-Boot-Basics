package com.blogapp.constants;

import com.blogapp.utils.RolesDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(
        using = RolesDeserializer.class
)
public enum Roles {
    ADMIN,
    USER
}
