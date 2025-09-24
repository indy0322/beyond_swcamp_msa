package com.ohgiraffers.userservice.service;

import com.ohgiraffers.userservice.dto.UserDTO;
import com.ohgiraffers.userservice.aggregate.UserEntity;
import com.ohgiraffers.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void registUser(UserDTO userDTO) {
        /* 설명. 회원가입 할 인원에게 고유 아이디 생성 */
        userDTO.setUserId(UUID.randomUUID().toString());

        /* 설명. ModelMapper를 활용할 때 필드명이 정확히 일치해야 하는 경우 추가 설정해 주어야 한다. */
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
        log.info("Service 계층에서 DTO -> Entity: {}", userEntity);

        /* 설명. UserDTO로 넘어온 사용자의 암호(평문)를 BCrypt 암호화 해서(다이제스트) UserEntity에 전달 */
        userEntity.setEncryptPwd(bCryptPasswordEncoder.encode(userDTO.getPwd()));

        userRepository.save(userEntity);
    }
}
