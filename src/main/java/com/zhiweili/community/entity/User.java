package com.zhiweili.community.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "user")
@Entity(name = "user")
public class User {

  @Id
  @Setter(value = AccessLevel.NONE)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "account_id")
  private String accountId;

  private String name;

  private String token;

  @Column(name = "gmt_create")
  private Long gmtCreate;

  @Column(name = "gmt_modified")
  private Long gmtModified;
}
