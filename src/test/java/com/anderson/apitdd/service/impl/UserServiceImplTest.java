package com.anderson.apitdd.service.impl;

import com.anderson.apitdd.dto.UserReqDto;
import com.anderson.apitdd.dto.UserRespDto;
import com.anderson.apitdd.entities.User;
import com.anderson.apitdd.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {
    public static final long ID = 1L;
    public static final String USER = "User";
    public static final String EMAIL = "EMAILUSER@EMAIL.COM";
    public static final String PASSWORD = "12345678";
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private UserRepository userRepository;
    @Mock
    private User user;
    @Mock
    private UserReqDto userReqDto;
    @Mock
    private UserRespDto userRespDto;

    Optional<User> optionalUser;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void findAllUser() {
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(optionalUser);
        Mockito.when(userService.findById(Mockito.anyLong())).thenReturn(userRespDto);
        UserRespDto userResp =userService.findById(ID);
        Assertions.assertEquals(UserRespDto.class,userResp.getClass());
        Assertions.assertEquals(ID,userResp.getId());
    }

    @Test
    void createUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
    private void startUser(){
        user=new User(ID, USER, EMAIL, PASSWORD);
        userReqDto=new UserReqDto(USER,EMAIL,PASSWORD);
        userRespDto=new UserRespDto(ID,USER,PASSWORD);
        optionalUser=Optional.of(new User(ID,USER,EMAIL,PASSWORD));
    }
}