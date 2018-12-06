package com.fanxl.order.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/6 0006 20:24
 */
public class JsonTool {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T fromJson(Class<T> tClass, String json) {
        try {
            return objectMapper.readValue(json, tClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T fromJson(TypeReference<T> tTypeReference, String json) {
        try {
            return objectMapper.readValue(json, tTypeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
