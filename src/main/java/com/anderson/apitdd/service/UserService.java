package com.anderson.apitdd.service;

import com.anderson.apitdd.dto.UserReqDto;
import com.anderson.apitdd.dto.UserRespDto;

import java.util.List;

public interface UserService {
    public List<UserRespDto> findAllUser();
    public UserRespDto findById(Long id);
    public UserRespDto createUser(UserReqDto userReqDto);
    public void updateUser(Long id,UserReqDto userReqDto);
    public void deleteUser(Long id,UserReqDto userReqDto);
    public UserRespDto loginUser(UserReqDto userReqDto);
}
