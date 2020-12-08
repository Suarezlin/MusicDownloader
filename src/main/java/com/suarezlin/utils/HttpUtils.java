package com.suarezlin.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;

public class HttpUtils {

    public static final OkHttpClient client = new OkHttpClient();

    private static final Headers headers = Headers.of("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36");

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String getTextResponse(String url) {
        Request request = new Request.Builder()
                .url(url)
                .headers(headers)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("请求 URL: " + url + " 失败: " + e.getMessage());
        }
    }

    public static ObjectNode getJsonResponse(String url) {
        String text = getTextResponse(url);
        ObjectNode res = null;
        try {
            res = objectMapper.readValue(text, ObjectNode.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("解析 JSON: " + text + " \n失败: " + e.getMessage());
        }
        return res;
    }

}
