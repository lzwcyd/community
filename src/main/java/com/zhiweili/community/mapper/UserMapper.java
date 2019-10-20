package com.zhiweili.community.mapper;

import com.zhiweili.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
  @Insert(
      "INSERT INTO user (name ,account_id ,token ,gmt_crate,gmt_modified) VALUES(#{name},#{accountId},#{token},#{gmtCrate},#{gmtModified});")
  void insert(User user);
}
