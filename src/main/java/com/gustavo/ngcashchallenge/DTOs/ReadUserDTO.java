package com.gustavo.ngcashchallenge.DTOs;

import com.gustavo.ngcashchallenge.ENUMs.Role;
import com.gustavo.ngcashchallenge.models.Account;

public record ReadUserDTO(String name, String username, Role role, Account account) {
}
