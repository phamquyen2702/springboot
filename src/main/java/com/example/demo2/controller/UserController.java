package com.example.demo2.controller;

import com.example.demo2.dto.response.ResponseMessage;
import com.example.demo2.dto.response.UserResponse;
import com.example.demo2.dto.response.UserResponseNCT;
import com.example.demo2.model.User;
import com.example.demo2.repository.IDepartmentRepository;
import com.example.demo2.service.IDepartmentService;
import com.example.demo2.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    public static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    IUserService iUserService;

    @Autowired
    IDepartmentRepository iDepartmentRepository;

    @GetMapping("/get-all")
    public ResponseEntity<List<UserResponse>> getAll() {
        List<UserResponse> userResponseList = new ArrayList<>();
        List<User> users = iUserService.getAll();
        for (User user : users) {
            UserResponse userResponse = new UserResponse();
            userResponseList.add(userResponse.converter(userResponse, user));
        }
        return new ResponseEntity<>(userResponseList, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") String id) {
        boolean status = iUserService.deleteUser(id);
        logger.info("Status delete " + status);
        return (status ? new ResponseEntity<>(new ResponseMessage("Deleted"), HttpStatus.OK) :
                new ResponseEntity<>(new ResponseMessage("Not deleted of id not found"), HttpStatus.OK));
    }

    @GetMapping("/get-by-department/{id}")
    public ResponseEntity<List<UserResponseNCT>> getByDepartment(@PathVariable("id") Long id, @RequestParam("page") int page, @RequestParam("size") int size) {

        List<UserResponseNCT> userResponseList = new ArrayList<>();
        List<User> users = iUserService.getByDepartmentId(id, page, size);
        for (User user : users) {
            UserResponseNCT userResponse = new UserResponseNCT();
            userResponseList.add(userResponse.converter(userResponse, user));
        }
        return new ResponseEntity<>(userResponseList, HttpStatus.OK);
    }

    @GetMapping("/get-by-role/{id}")
    public ResponseEntity<List<UserResponse>> getByRoles(@PathVariable("id") Long id, @RequestParam("page") int page, @RequestParam("size") int size) {
        List<UserResponse> users = iUserService.getByRoleId(id, page, size);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam("departmentId") Long departmentId, @RequestParam("userId") String userId) {
        if (!iUserService.existsById(userId)) {
            return new ResponseEntity<>(new ResponseMessage("Not found User by Id, please try again!"), HttpStatus.OK);
        }
        if (!iDepartmentRepository.existsById(departmentId)) {
            return new ResponseEntity<>(new ResponseMessage("Not found User by Id, please try again!"), HttpStatus.OK);
        }
        iUserService.save(departmentId, userId);
        return new ResponseEntity<>(new ResponseMessage("Update success!"), HttpStatus.OK);
    }
}
