package com.anderson.apitdd.controller;

import com.anderson.apitdd.dto.UserReqDto;
import com.anderson.apitdd.dto.UserRespDto;
import com.anderson.apitdd.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<List<UserRespDto>> findAllUser(){
        return ResponseEntity.ok().body(userService.findAllUser());

    }
    @GetMapping("/{id}")
    public ResponseEntity<UserRespDto> findById(@PathVariable("id") Long id){
      return ResponseEntity.ok().body(userService.findById(id));

    }
    @PostMapping
    public ResponseEntity<UserRespDto> creteUser(@RequestBody @Valid UserReqDto userReqDto){
        return ResponseEntity.ok().body(userService.createUser(userReqDto));

    }
}
