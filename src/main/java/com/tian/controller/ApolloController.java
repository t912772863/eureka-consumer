package com.tian.controller;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.tian.common.ApolloConfig;
import com.tian.common.UserProConfigBean;
import com.tian.common.other.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by z on 2019/9/4.
 */
@Controller
public class ApolloController {
    /**
     * 从配置中心获取配置值, :后面表示当没有配置时的默认值.
     * 基于apollo的配置是可以动态刷新的.
     */
    @Value("${apolloValue:defaultValue}")
    private String apolloValue;
    @Autowired
    UserProConfigBean userProConfigBean;

    @RequestMapping("getValue")
    @ResponseBody
    public ResponseData getValue(){
        Config config = ConfigService.getAppConfig();
        for(String s:config.getPropertyNames()){
            System.out.println(s+" : "+config.getProperty(s,""));
        }
        System.out.println(apolloValue);

        Config config1 = ConfigService.getConfig("application");
        config1.getPropertyNames();

        return ResponseData.successData;
    }
}
