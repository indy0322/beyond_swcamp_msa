package com.ohgiraffers.userservice.service;

import com.ohgiraffers.userservice.dto.UserDTO;
import org.springframework.stereotype.Service;

public interface UserService {

    void registUser(UserDTO userDTO);
}
