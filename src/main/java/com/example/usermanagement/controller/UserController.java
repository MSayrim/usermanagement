package com.example.usermanagement.controller;

import com.example.usermanagement.dto.constant.ShortUserDTO;
import com.example.usermanagement.dto.constant.UserDTO;
import com.example.usermanagement.dto.request.GenericServiceResult;
import com.example.usermanagement.dto.request.UserFilterDTO;
import com.example.usermanagement.service.UserService;
import com.example.usermanagement.enums.ResponseStatus;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<GenericServiceResult> createOrUpdatePersonnel(@RequestBody UserDTO personnelDTO){
        UserDTO personnel = userService.createOrUpdate(personnelDTO);
        return new ResponseEntity<>(new GenericServiceResult(ResponseStatus.SUCCESS, ResponseStatus.SUCCESS.getResultCode(), ResponseStatus.SUCCESS.toString(), personnel), HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<GenericServiceResult> filterPersonnel(@RequestBody UserFilterDTO userFilterDTO){
        Page<UserDTO> userDTOList = userService.filter(userFilterDTO);
        return new ResponseEntity<>(new GenericServiceResult(ResponseStatus.SUCCESS, ResponseStatus.SUCCESS.getResultCode(), ResponseStatus.SUCCESS.toString(), userDTOList), HttpStatus.OK);
    }

    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<GenericServiceResult> getPersonnel(@PathVariable String uuid){
        UserDTO userDTO = userService.getSpecificPersonnel(uuid);
        return new ResponseEntity<>(new GenericServiceResult(ResponseStatus.SUCCESS, ResponseStatus.SUCCESS.getResultCode(), ResponseStatus.SUCCESS.toString(), userDTO), HttpStatus.OK);
    }

    @GetMapping("/get-by-role/{role}")
    public ResponseEntity<GenericServiceResult> getAllPersonnelsByRole(@PathVariable String role){
        List<ShortUserDTO> users = userService.getAllPersonnelsByRole(role);
        return new ResponseEntity<>(new GenericServiceResult(ResponseStatus.SUCCESS, ResponseStatus.SUCCESS.getResultCode(), ResponseStatus.SUCCESS.toString(), users), HttpStatus.OK);
    }




}