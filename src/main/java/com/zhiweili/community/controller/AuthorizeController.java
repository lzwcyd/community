package com.zhiweili.community.controller;

import com.zhiweili.community.dto.AccessTokenDto;
import com.zhiweili.community.dto.GiteeUserDto;
import com.zhiweili.community.mapper.UserMapper;
import com.zhiweili.community.model.User;
import com.zhiweili.community.provider.GiteeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GiteeProvider giteeProvider;
    private UserMapper userMapper;

    @Value("${gitee.client.id}")
    private String clientId;

    @Value("${gitee.client.secret}")
    private String clientSecret;

    @Value("${gitee.Redirect.uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           HttpServletRequest request){
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(clientSecret);
        accessTokenDto.setCode(code);
        accessTokenDto.setState("1");
        accessTokenDto.setGrant_type("authorization_code");
        accessTokenDto.setRedirect_uri(redirectUri);
        String accessToken = giteeProvider.getAccessToken(accessTokenDto);
        GiteeUserDto giteeUserDto = giteeProvider.getUser(accessToken);
        if (giteeUserDto!=null){
            User user = new User();
            user.setToken(UUID.randomUUID().toString());
            user.setAccountId(String.valueOf(giteeUserDto.getId()));
            user.setName(giteeUserDto.getName());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            try{
                userMapper.insert(user);
            }catch (Exception e){
            }
            request.getSession().setAttribute("user",giteeUserDto);
            return "redirect:index";
        }else {
            return "redirect:index";
        }
    }


}
