package com.zhiweili.community.service;

import com.zhiweili.community.entity.User;
import com.zhiweili.community.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    //通过token查询用户
    public User findByToken(String token){
        User user = new User();
        user = userRepository.findByToken(token);
        return user;
    }

}
