package com.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author rcz
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class CommunityApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(CommunityApplication.class, args);
    }
}