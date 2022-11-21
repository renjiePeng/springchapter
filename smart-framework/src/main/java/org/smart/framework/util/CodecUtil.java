package org.smart.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @PackageName: org.smart.framework.util
 * @Author 彭仁杰
 * @Date 2022/11/21 14:50
 * @Description
 **/
public final class CodecUtil {
    private static final Logger logger =  LoggerFactory.getLogger(CodecUtil.class);
    public static String encodeURL(String source){
        String target;
        try {
            target = URLEncoder.encode(source,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("encode url failure",e);
            throw new RuntimeException(e);
        }
        return target;
    }

    public static String decodeURL(String source){
        String target;
        try {
            target = URLDecoder.decode(source,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("decode url failure",e);
            throw new RuntimeException(e);
        }
        return target;
    }
}
