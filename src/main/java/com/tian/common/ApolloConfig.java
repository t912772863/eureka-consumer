package com.tian.common;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by z on 2019/9/4.
 * 启用apollo动态配置刷新功能.
 */
@Configuration
@EnableApolloConfig
public class ApolloConfig {
    @Bean
    public UserProConfigBean getUserProConfigBean(){
        return new UserProConfigBean();
    }

}
