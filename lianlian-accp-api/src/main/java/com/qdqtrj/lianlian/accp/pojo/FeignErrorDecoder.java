package com.qdqtrj.lianlian.accp.pojo;

import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author yinbin
 * @version 1.0
 * @date 2021/4/15 11:27 上午
 */
@Configuration
public class FeignErrorDecoder implements ErrorDecoder {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Exception decode(String methodKey, Response response) {
        Exception exception = null;
        try {
            String json = Util.toString(response.body().asReader(Charset.defaultCharset()));
            exception = new RuntimeException(json);
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
        return exception;
    }
}
