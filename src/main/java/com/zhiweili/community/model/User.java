package com.zhiweili.community.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private Integer id;

    private String accountId;

    private String name;

    private String token;

    private Long gmtCreate;

    private Long gmtModified;


}
