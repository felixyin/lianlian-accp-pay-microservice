package com.qdqtrj.lianlian.accp;

import com.qdqtrj.lianlian.accp.repo.impl.BaseDaoImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author yinbin
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EnableConfigurationProperties
@EnableMongoRepositories(repositoryBaseClass = BaseDaoImpl.class)
@Slf4j
public class PayServerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PayServerApplication.class, args);
    }

    /**
     * @param args
     */
    @Override
    public void run(String... args) {
        log.info("************************************************************************");
        log.info("********************* 支付(lianlian-accp-server)启动完成！*********************");
        log.info("************************************************************************");
    }
}
