package com.tian.remote;

import com.tian.common.other.ResponseData;
import com.tian.dao.entity.TestEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    /**
     * Feign将方法签名中方法参数对象序列化为请求参数放到Http请求中的过程, 是由编码器Encoder完成的.
     * 同理,将 Http响应中的数据反序列化为java对象是由解码器Decoder完成的.
     * 默认情况下, Feign会将@PathVariable标记的参数与路径{}进行绑定, 将@RequestParam参数转换成字
     * 符串添加到url中, 将没有注解的参数, 例如(testEntity), 通过jackson转换成json放到请求体中.
     * 注意, 如果这个时候,用的是get请求, 则放入请求消息体中的json会被忽略
     *
     * @param id
     * @param a
     * @param testEntity
     * @return
     */
    @RequestMapping(value = "/queryById3/{id}",method = RequestMethod.POST)
    ResponseData queryById3(@PathVariable("id")Long id, @RequestParam(value = "a") String a,TestEntity testEntity);

//    @RequestMapping(value = "/insert", method = RequestMethod.POST)
//    ResponseData insertUserInfo(@RequestBody UserInfo userInfo);
}
