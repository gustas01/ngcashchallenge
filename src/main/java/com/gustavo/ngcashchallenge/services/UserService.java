package com.gustavo.ngcashchallenge.services;

import com.gustavo.ngcashchallenge.DTOs.ReadUserDTO;
import com.gustavo.ngcashchallenge.mappers.UserMapper;
import com.gustavo.ngcashchallenge.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private UserRepository userRepository;
  private UserMapper userMapper;

  public UserService(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  public ResponseEntity<ReadUserDTO> read(String username){
    ReadUserDTO readUserDTO = userMapper.UserToReadUserDTO(userRepository.findByusername(username));
    if(readUserDTO == null) return new ResponseEntity("Usuário não encontrado", HttpStatus.BAD_REQUEST);
    return ResponseEntity.ok(readUserDTO);
  }
}
