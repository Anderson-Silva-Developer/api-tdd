package com.anderson.apitdd.service;

import com.anderson.apitdd.dto.UserReqDto;
import com.anderson.apitdd.dto.UserRespDto;

public interface UserService {
    public UserRespDto findById(Long id);
    public UserRespDto createUser(UserReqDto userReqDto);
}
