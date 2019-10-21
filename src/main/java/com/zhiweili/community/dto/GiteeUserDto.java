package com.zhiweili.community.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class GiteeUserDto {
  private String name;
  private Long id;
  private String bio;
}
