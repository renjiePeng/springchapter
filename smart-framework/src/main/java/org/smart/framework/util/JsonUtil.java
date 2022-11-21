package org.smart.framework.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @PackageName: org.smart.framework.util
 * @Author 彭仁杰
 * @Date 2022/11/21 15:10
 * @Description Json 工具类
 **/
public class JsonUtil {
    private static final Logger logger =  LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 将POJO转为JSON
     * @param obj json
     * @param <T> 对象
     * @return T
     */
    public static <T> String toJson(T obj){
        String json;
        try {
            json = OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("convert POJO to JSON failure",e);
            throw new RuntimeException(e);
        }
        return json;
    }

    /**
     * 将JSON转为POJO
     * @param json json
     * @param type 类型
     * @param <T> T
     * @return T
     */
    public static <T> T fromJson(String json,Class<T> type){
        T pojo;
        try {
            pojo = OBJECT_MAPPER.readValue(json,type);
        } catch (IOException e) {
            logger.error("convert JSON to JSON failure",e);
            throw new RuntimeException(e);
        }
        return pojo;
    }
}
