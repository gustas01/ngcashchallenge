package com.gustavo.ngcashchallenge.mappers;

import com.gustavo.ngcashchallenge.DTOs.CreateUserDTO;
import com.gustavo.ngcashchallenge.DTOs.ReadUserDTO;
import com.gustavo.ngcashchallenge.models.Account;
import com.gustavo.ngcashchallenge.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper{

  public void UserToCreateUserDTO(User user){

  }

  public User CreateUserDTOtoUser(CreateUserDTO userDTO){
    User user = new User();
    user.setName(userDTO.name());
    user.setUsername(userDTO.username());
    user.setPassword(userDTO.password());
    Account account = new Account();
    user.setAccount(account);

    return user;
  }

  public ReadUserDTO UserToReadUserDTO(User user){
    if (user == null) return null;
    ReadUserDTO readUserDTO = new ReadUserDTO(user.getName(), user.getUsername(), user.getRole(), user.getAccount());
    return readUserDTO;
  }
}
