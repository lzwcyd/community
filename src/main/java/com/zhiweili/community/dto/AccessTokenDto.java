package com.zhiweili.community.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccessTokenDto {
    private String client_id;
    private String redirect_uri;
    private String client_secret;
    private String code;
    private String grant_type;
    private String state;
}
