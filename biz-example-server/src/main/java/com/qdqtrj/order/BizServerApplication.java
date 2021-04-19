package com.qdqtrj.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author yinbin
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EnableConfigurationProperties
@Slf4j
public class BizServerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BizServerApplication.class, args);
    }

    /**
     * @param args
     */
    @Override
    public void run(String... args) {
        log.info("************************************************************************");
        log.info("********************* 支付例子(biz-example-server)启动完成！*********************");
        log.info("************************************************************************");
    }
}
