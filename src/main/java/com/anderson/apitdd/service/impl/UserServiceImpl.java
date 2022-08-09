package com.anderson.apitdd.service.impl;

import com.anderson.apitdd.dto.UserReqDto;
import com.anderson.apitdd.dto.UserRespDto;
import com.anderson.apitdd.entities.User;
import com.anderson.apitdd.exception.BadRequestGeneric;
import com.anderson.apitdd.exception.NotFoundGeneric;
import com.anderson.apitdd.repository.UserRepository;
import com.anderson.apitdd.service.UserService;
import com.anderson.apitdd.util.Cryptography;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UserRespDto> findAllUser() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserRespDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserRespDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundGeneric("Usuário não encontrado!"));
        return modelMapper.map(user, UserRespDto.class);
    }
    @Transactional
    @Override
    public UserRespDto createUser(UserReqDto userReqDto) {
        Optional<User> isUser = userRepository.findByEmailIgnoreCase(userReqDto.getEmail());
        if (isUser.isPresent()) {
            throw new BadRequestGeneric("O E-mail fornecido já está vinculado a um Usuário");
        }
        userReqDto.setPassword(Cryptography.md5(userReqDto.getPassword()));
        userReqDto.setEmail(userReqDto.getEmail().toUpperCase());
        User user = modelMapper.map(userReqDto, User.class);
        user = userRepository.save(user);
        return modelMapper.map(user, UserRespDto.class);
    }
    @Transactional
    @Override
    public void updateUser(Long id, UserReqDto userReqDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundGeneric("Usuário não encontrado!"));
        if(!equalProperties(user,userReqDto)){
            validateUpdateUser(user, userReqDto);
            user.setName(userReqDto.getName());
            user.setEmail(userReqDto.getEmail().toUpperCase());
            userRepository.save(user);
        }

    }

    @Transactional
    @Override
    public void deleteUser(Long id, UserReqDto userReqDto) {
        User user=validateUser(id,userReqDto);
        if(Objects.nonNull(user)) {
            userRepository.delete(user);
        }
    }
    private User validateUser(Long id, UserReqDto userReqDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundGeneric("Usuário não encontrado!"));
        if(!user.getEmail().equalsIgnoreCase(userReqDto.getEmail())){
            throw new BadRequestGeneric("E-mail incorreto!");
        }
        if (!user.getPassword().equals(Cryptography.md5(userReqDto.getPassword()))) {
            throw new BadRequestGeneric("Senha ou Usuário incorreto!");
        }
        return user;

    }

    private void validateUpdateUser(User user, UserReqDto userReqDto) {

        if(!user.getEmail().equalsIgnoreCase(userReqDto.getEmail())){
            User isUser = findByUser(userReqDto.getEmail());
            if (Objects.nonNull(isUser)) {
                throw new BadRequestGeneric("Este E-mail já está vinculado a um Usuario!");
            }
        }
        if (!user.getPassword().equals(Cryptography.md5(userReqDto.getPassword()))) {
            throw new BadRequestGeneric("Senha ou Usuário incorreto!");
        }

    }
    private Boolean equalProperties(User user,UserReqDto userReqDto){
        return user.getEmail().equalsIgnoreCase(userReqDto.getEmail())
                && user.getPassword().equals(Cryptography.md5(userReqDto.getPassword()))
                && user.getName().equals(userReqDto.getName());

    }

    private User findByUser(String email) {
        User user = userRepository.findByEmailIgnoreCase(email).orElse(null);
        return user;
    }
}
