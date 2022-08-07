package com.anderson.apitdd.service.impl;

import com.anderson.apitdd.dto.UserReqDto;
import com.anderson.apitdd.dto.UserRespDto;
import com.anderson.apitdd.entities.User;
import com.anderson.apitdd.exception.BadRequestGeneric;
import com.anderson.apitdd.exception.NotFoundGeneric;
import com.anderson.apitdd.repository.UserRepository;
import com.anderson.apitdd.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserRespDto findById(Long id) {
        User user=userRepository.findById(id).orElseThrow(()-> new NotFoundGeneric("Usuario não encontrado!"));
        return modelMapper.map(user,UserRespDto.class);
    }

    @Override
    public UserRespDto createUser(UserReqDto userReqDto) {
        Optional<User> isUser=userRepository.findByEmail(userReqDto.getEmail());
        if(isUser.isPresent()){
           throw  new BadRequestGeneric("O e-mail fornecido já está vinculado a um usuario");
        }
        User user=modelMapper.map(userReqDto,User.class);
        user=userRepository.save(user);
        return modelMapper.map(user,UserRespDto.class);
    }
}
