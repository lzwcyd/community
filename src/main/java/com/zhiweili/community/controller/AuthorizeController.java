package com.zhiweili.community.controller;

import com.zhiweili.community.dto.AccessTokenDto;
import com.zhiweili.community.dto.GiteeUserDto;
import com.zhiweili.community.provider.GiteeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GiteeProvider giteeProvider;

    @Value("${gitee.client.id}")
    private String clientId;

    @Value("${gitee.client.secret}")
    private String clientSecret;

    @Value("${gitee.Redirect.uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code){
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(clientSecret);
        accessTokenDto.setCode(code);
        accessTokenDto.setGrant_type("authorization_code");
        accessTokenDto.setRedirect_uri(redirectUri);
        String accessToken = giteeProvider.getAccessToken(accessTokenDto);
        GiteeUserDto user = giteeProvider.getUser(accessToken);
        return "index";
    }


}
