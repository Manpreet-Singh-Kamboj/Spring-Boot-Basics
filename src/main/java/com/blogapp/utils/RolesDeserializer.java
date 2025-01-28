package com.blogapp.utils;

import com.blogapp.constants.Roles;
import com.blogapp.exceptions.InvalidRoleException;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class RolesDeserializer extends JsonDeserializer<Roles> {
    @Override
    public Roles deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        String role = parser.getText();
        try{
            return Roles.valueOf(role);
        }catch (IllegalArgumentException exception){
            throw new InvalidRoleException(role);
        }
    }
}
