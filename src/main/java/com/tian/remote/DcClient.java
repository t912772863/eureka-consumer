package com.tian.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 由于引入了feign jar功能, 这里可以声明一个接口, 通过注解的方式, 与远程接口关联起来.
 * 这样本地在进行调用的时候可以类似于注入一个普通的service层
 * Created by Administrator on 2018/7/31 0031.
 */
@FeignClient("eureka-client")
public interface DcClient {

    @GetMapping("/dc")
    String consumer();

}
