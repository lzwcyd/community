package com.zhiweili.community.controller;

import com.alibaba.fastjson.JSON;
import com.zhiweili.community.dto.AccessTokenDto;
import com.zhiweili.community.dto.GiteeUserDto;
import com.zhiweili.community.entity.User;
import com.zhiweili.community.provider.GiteeProvider;
import com.zhiweili.community.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class AuthorizeController {

  @Autowired private GiteeProvider giteeProvider;
  @Autowired private UserRepository userRepository;

  @Value("${gitee.client.id}")
  private String clientId;

  @Value("${gitee.client.secret}")
  private String clientSecret;

  @Value("${gitee.Redirect.uri}")
  private String redirectUri;

  @GetMapping("/callback")
  public ResponseEntity callback(
      @RequestParam(name = "code") String code, HttpServletRequest request) {
    AccessTokenDto accessTokenDto = new AccessTokenDto();
    accessTokenDto.setClient_id(clientId);
    accessTokenDto.setClient_secret(clientSecret);
    accessTokenDto.setCode(code);
    accessTokenDto.setState("1");
    accessTokenDto.setGrant_type("authorization_code");
    accessTokenDto.setRedirect_uri(redirectUri);
    String accessToken = giteeProvider.getAccessToken(accessTokenDto);
    GiteeUserDto giteeUserDto = giteeProvider.getUser(accessToken);
    if (giteeUserDto != null) {
      User user = new User();
      user.setToken(UUID.randomUUID().toString());
      user.setAccountId(String.valueOf(giteeUserDto.getId()));
      user.setName(giteeUserDto.getName());
      user.setGmtCreate(System.currentTimeMillis());
      user.setGmtModified(user.getGmtCreate());
      userRepository.save(user);
      request.getSession().setAttribute("user", giteeUserDto);
      return ResponseEntity.ok(user);
    } else {
      return ResponseEntity.badRequest().body(null);
    }
  }
}
