package com.demo.service.saturday.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;


/**
 * Created by aizen on 19-6-26.
 */
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableCaching
public class SaturdayApiApplicationContext {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(SaturdayApiApplicationContext.class);

        try {
//            SpringApplication.run(SaturdayApiApplicationContext.class, args);
            SpringApplication springApplication = new SpringApplication(SaturdayApiApplicationContext.class);
            springApplication.setRegisterShutdownHook(false);
            ConfigurableApplicationContext ctx = springApplication.run(args);
            Runtime.getRuntime().addShutdownHook(
                    new Thread(() -> {
                        try {
                            Thread.sleep(10000L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        ctx.close();
                    }));
        } catch (Exception e) {
            logger.error("init error", e);
            System.exit(-1);
        }
    }

    @Bean
    public ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver() {
        return new ExceptionHandlerExceptionResolver();
    }
}
