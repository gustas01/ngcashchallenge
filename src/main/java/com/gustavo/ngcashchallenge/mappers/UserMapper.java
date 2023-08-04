package com.gustavo.ngcashchallenge.mappers;

import com.gustavo.ngcashchallenge.DTOs.CreateUserDTO;
import com.gustavo.ngcashchallenge.models.Account;
import com.gustavo.ngcashchallenge.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper{

  public void UserToCreateUserDTO(User user){

  }

  public User CreateUserDTOtoUser(CreateUserDTO userDTO){
    //TODO: fazer o hash da senha antes de salvar (linha comentada), mas precisa instalar o security primeiro
    User user = new User();
    user.setName(userDTO.name());
    user.setUsername(userDTO.username());
    user.setPassword(userDTO.password());
    Account account = new Account();
    user.setAccount(account);
//    String hashPassword = new BCryptPassEncoder().encode(userDTO.password());

    return user;
  }
}
