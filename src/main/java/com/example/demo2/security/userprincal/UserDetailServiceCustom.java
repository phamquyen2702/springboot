package com.example.demo2.security.userprincal;

import com.example.demo2.model.User;
import com.example.demo2.repository.IUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.transaction.Transactional;
@Service
public class UserDetailServiceCustom implements UserDetailsService {
    public static final Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);
    @Autowired
    IUserRepository userRepository;
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow( () -> new UsernameNotFoundException("User not fund" + username));
        logger.info("Thông tin user: "+ user.getPassword());
        return UserPrinciple.build(user);
    }
}
