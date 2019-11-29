package com.vitily.cluster.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 启动做的一些外工作
 */
@Component
@Configuration
@ComponentScan({"com.vitily.cluster.gateway.config"})
@Slf4j
public class StartupController implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {}
















}
