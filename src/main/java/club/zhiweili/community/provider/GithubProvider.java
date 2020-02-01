package club.zhiweili.community.provider;

import club.zhiweili.community.dto.AccessTokenDto;
import club.zhiweili.community.dto.GithubUserDto;
import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDto accessTokenDto){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
//        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDto));
        RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDto),mediaType);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public GithubUserDto getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string =response.body().string();
            GithubUserDto githubUser = JSON.parseObject(string, GithubUserDto.class);
            return githubUser;
        } catch (IOException e) {
        }
        return null;
    }
}
