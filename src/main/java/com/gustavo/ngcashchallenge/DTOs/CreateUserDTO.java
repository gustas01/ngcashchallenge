package com.gustavo.ngcashchallenge.DTOs;

import com.gustavo.ngcashchallenge.ENUMs.Role;

public record CreateUserDTO(String name, String username, String password, Role role) {
}
