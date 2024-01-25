package com.example.demo2.controller;

import com.example.demo2.dto.request.LoginForm;
import com.example.demo2.dto.request.SignUpForm;
import com.example.demo2.dto.response.JwtResponse;
import com.example.demo2.dto.response.ResponseMessage;
import com.example.demo2.model.Role;
import com.example.demo2.model.User;
import com.example.demo2.security.jwt.JwtEntryPoint;
import com.example.demo2.security.jwt.JwtPrivicer;
import com.example.demo2.security.userprincal.UserPrinciple;
import com.example.demo2.service.IRoleService;
import com.example.demo2.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    public static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    IUserService iUserService;
    @Autowired
    IRoleService iRoleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtPrivicer jwtPrivicer;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody SignUpForm signUpForm) {
        if (iUserService.existsByUsername(signUpForm.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("The Username existed, Please try again!"), HttpStatus.OK);
        }
        if (iUserService.existsByEmail(signUpForm.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("The email existed, Please try again!"), HttpStatus.OK);
        }
        User user = new User();
        user.setFullname(signUpForm.getFullname());
        user.setUsername(signUpForm.getUsername());
        user.setEmail(signUpForm.getEmail());
        user.setPassword(passwordEncoder.encode(signUpForm.getPassword()));

        Set<String> signupRoles = signUpForm.getRoles();
        Set<Role> roles = new HashSet<>();
        for (String role : signupRoles) {
            switch (role) {
                case "admin":
                    Role adminRole = iRoleService.findByName(role).orElseThrow(() -> new RuntimeException("Role not found"));
                    roles.add(adminRole);
                    break;
                case "pm":
                    Role pmRole = iRoleService.findByName(role).orElseThrow(() -> new RuntimeException("Role not found"));
                    roles.add(pmRole);
                    break;
                default:
                    Role userRole = iRoleService.findByName(role).orElseThrow(() -> new RuntimeException("Role not found"));
                    roles.add(userRole);

            }
        }
        user.setRoles(roles);
        iUserService.save(user);
        return new ResponseEntity<>(new ResponseMessage("Signup user success!"), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Validated @RequestBody LoginForm loginForm) {
        logger.error("Da qua day 0");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));
        logger.error("Da qua day 1");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtPrivicer.createToken(authentication);
        logger.error("Da qua day 2");
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        logger.error("Da qua day 3");
        return ResponseEntity.ok(new JwtResponse(token, userPrinciple.getFullname(), userPrinciple.getAuthorities()));
    }
}
