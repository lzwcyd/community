package com.zhiweili.community.provider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhiweili.community.dto.AccessTokenDto;
import com.zhiweili.community.dto.GiteeUserDto;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GiteeProvider {
  public String getAccessToken(AccessTokenDto accessTokenDto) {
    MediaType mediaType = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();
    RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDto), mediaType);
    Request request = new Request.Builder().url("https://gitee.com/oauth/token").post(body).build();
    try {
      Response response = client.newCall(request).execute();
      String string = response.body().string();
      JSONObject jsonObject = JSONObject.parseObject(string);
      String access_token = jsonObject.getString("access_token");
      return access_token;
    } catch (IOException e) {
    }
    return null;
  }

  public GiteeUserDto getUser(String accessToken) {
    OkHttpClient client = new OkHttpClient();
    Request request =
        new Request.Builder()
            .url("https://gitee.com/api/v5/user?access_token=" + accessToken + "&state=1")
            .build();
    try {
      Response response = client.newCall(request).execute();
      String string = response.body().string();
      GiteeUserDto giteeUserDto = JSON.parseObject(string, GiteeUserDto.class);
      return giteeUserDto;
    } catch (IOException e) {
    }
    return null;
  }
}
