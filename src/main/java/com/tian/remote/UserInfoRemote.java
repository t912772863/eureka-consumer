package com.tian.remote;

import com.tian.common.other.ResponseData;
import com.tian.dao.entity.TestEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Administrator on 2018/8/2 0002.
 */
@FeignClient("eureka-client")
@RequestMapping("userInfo")
public interface UserInfoRemote {
    /**
     * 带参数的远程调用示例, restfull风格
     * @param id
     * @return
     */
    @RequestMapping("/queryById/{id}")
    String queryById(@RequestParam("id") Long id);

    @RequestMapping("/queryById2/{id}")
    TestEntity queryById2(@RequestParam("id")Long id);

    @RequestMapping("/queryById3/{id}")
    ResponseData queryById3(@RequestParam("id")Long id);
}
