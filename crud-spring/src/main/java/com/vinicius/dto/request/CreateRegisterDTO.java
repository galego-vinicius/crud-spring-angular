package com.vinicius.dto.request;

import com.vinicius.entities.ENUMS.UsersRole;

public record CreateRegisterDTO(String email, String password, UsersRole role) {
}
