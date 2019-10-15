package com.zhiweili.community.controller;

import com.zhiweili.community.dto.AccessTokenDto;
import com.zhiweili.community.dto.GiteeUserDto;
import com.zhiweili.community.provider.GiteeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GiteeProvider giteeProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code){
        System.out.println(code);
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setClient_id("fde18c7491c1c81c90a3cb46022ad307dceee1b1bdc68d66fb64acaeab613034");
        accessTokenDto.setClient_secret("db0e5b63807d8a8f8de5e9e1017e27c5509cded8725c875998f3745f1e57fa88");
        accessTokenDto.setCode(code);
        accessTokenDto.setGrant_type("authorization_code");
        accessTokenDto.setRedirect_uri("http://localhost:8080/callback");
        String accessToken = giteeProvider.getAccessToken(accessTokenDto);
        GiteeUserDto user = giteeProvider.getUser(accessToken);
        return "index";
    }


}
