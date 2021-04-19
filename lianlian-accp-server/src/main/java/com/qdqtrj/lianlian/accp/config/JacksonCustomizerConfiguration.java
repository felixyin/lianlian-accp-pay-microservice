package com.qdqtrj.lianlian.accp.config;

import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * 解决spring默认json处理报错：SerializationFeature.FAIL_ON_EMPTY_BEANS
 * fastjson不报错，但spring默认使用jackson处理json
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/23 7:50 下午
 */
@Configuration
public class JacksonCustomizerConfiguration {
    @Bean
    Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return new Jackson2ObjectMapperBuilderCustomizer() {
            @Override
            public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
                jacksonObjectMapperBuilder.featuresToDisable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            }
        };
    }
}
