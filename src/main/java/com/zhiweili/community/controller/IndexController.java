package com.zhiweili.community.controller;

import com.zhiweili.community.entity.User;
import com.zhiweili.community.repository.UserRepository;
import com.zhiweili.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String index(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
    for (Cookie cookie : cookies) {
      if (cookie.getName().equals("token ")){
          String token = cookie.getValue();
          User user = userService.findByToken(token);
          if (user != null){
              request.getSession().setAttribute("user",user);
          }
          break;
      }
    }
        return "index";
    }
}
