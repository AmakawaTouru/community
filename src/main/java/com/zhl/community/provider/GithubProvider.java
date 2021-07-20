package com.zhl.community.provider;

import com.alibaba.fastjson.JSON;
import com.zhl.community.dto.AccessTokenDTO;
import com.zhl.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;

@Component
public class GithubProvider {

    public OkHttpClient.Builder builder;
    {
         builder = new OkHttpClient.Builder();
         //代理服务器的IP和端口号
         builder.proxy(new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("127.0.0.1", 10808)));
    }
    /**
     * 利用OkHttp获取accessToken
     * @param accessTokenDTO
     * @return
     */
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = builder.build();
//        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            String access_token = response.body().string().split("&")[0].split("=")[1];
            return access_token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 根据accessToken访问得到user信息。并将User封装为一个GitHubUser
     * @param accessToken
     * @return
     */
    public GithubUser getUser(String accessToken){
        OkHttpClient client = builder.build();
//        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                //github新版请求方式
                .url("https://api.github.com/user")
                .header("Authorization","token "+accessToken)
//               .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            System.out.println(string);
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
        }
        return null;
    }

}
