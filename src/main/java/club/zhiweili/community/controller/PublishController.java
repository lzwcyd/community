package club.zhiweili.community.controller;

import club.zhiweili.community.entity.Question;
import club.zhiweili.community.entity.User;
import club.zhiweili.community.repository.QuestionRepository;
import club.zhiweili.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/** Created by lzw on 2020/2/1 9:53 上午 */
@Controller
public class PublishController {

//  @Autowired QuestionService questionService;
  @Autowired
  QuestionRepository questionRepository;
  @Autowired UserService userService;

  @GetMapping("/publish")
  public String publish() {
    return "publish";
  }

  @PostMapping("/publish")
  public String doPublish(
      @RequestParam("title") String title,
      @RequestParam("description") String description,
      @RequestParam("tag") String tag,
      HttpServletRequest request,
      Model model) {
    model.addAttribute("title",title);
    model.addAttribute("description",description);
    model.addAttribute("tag",tag);
    if (title == null || title == ""){
      model.addAttribute("error", "标题不能为空");
      return "publish";
    }
    if (description == null|| description == ""){
      model.addAttribute("error", "问题补充不能为空");
      return "publish";
    }
    if (tag == null|| tag == ""){
      model.addAttribute("error", "标签不能为空");
      return "publish";
    }
    User user = null;
    Cookie[] cookies = request.getCookies();
    for (Cookie cookie : cookies) {
      if (cookie.getName().equals("token")) {
        String token = cookie.getValue();
        user = userService.findByToken(token);
        if (user != null) {
          request.getSession().setAttribute("user", user);
        }
        break;
      }
    }
    if (user == null) {
      model.addAttribute("error", "用户未登陆");
      return "/publish";
    }
    Question question = new Question();
    question.setTitle(title);
    question.setDescription(description);
    question.setTag(tag);
    question.setGmtCreate(System.currentTimeMillis());
    question.setCreator(user.getId());
    question.setGmtModified(question.getGmtCreate());
    questionRepository.save(question);
    return "redirect:";
  }
}
