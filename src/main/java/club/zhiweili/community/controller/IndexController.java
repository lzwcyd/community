package club.zhiweili.community.controller;

import club.zhiweili.community.dto.QuestionDto;
import club.zhiweili.community.entity.User;
import club.zhiweili.community.service.QuestionService;
import club.zhiweili.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
  @Autowired UserService userService;
  @Autowired QuestionService questionService;

  @GetMapping("/index")
  public String index(
      HttpServletRequest request,
      Model model,
      @RequestParam(name = "page", defaultValue = "1") Integer page,
      @RequestParam(name = "size", defaultValue = "5") Integer size) {
    Cookie[] cookies = request.getCookies();
    if (cookies != null && cookies.length != 0) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals("token")) {
          String token = cookie.getValue();
          User user = userService.findByToken(token);
          if (user != null) {
            request.getSession().setAttribute("user", user);
          }
          break;
        }
      }
    }
    List<QuestionDto> questionDtoList = questionService.list(page,size);
    model.addAttribute("questions", questionDtoList);
    return "index";
  }
  @GetMapping("/")
  public String album(){
    return "album";
  }
}
