package com.gustavo.ngcashchallenge.mappers;

import com.gustavo.ngcashchallenge.DTOs.CreateUserDTO;
import com.gustavo.ngcashchallenge.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public void UserToDTO(User user){

  }

  public<T> User DTOtoUser(CreateUserDTO userDTO){
    User user = new User();
//    user.setName(userDTO.name());
//    user.setUsername(userDTO.username());
////    String hashPassword = new BCryptPassEncoder().encode(userDTO.password());

    return user;
  }
}
