package com.tian.controller;

import com.tian.common.other.ResponseData;
import com.tian.dao.entity.TestEntity;
import com.tian.remote.DcClient;
import com.tian.remote.UserInfoRemote;
import com.tian.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * Creaed by Administrator on 2018/7/31 0031.
 */
@RestController
public class DcController {
    @Autowired
    LoadBalancerClient loadBalancerClient;
    @Resource(name = "restTemplate")
    RestTemplate restTemplate;
    @Resource(name = "restTemplate1")
    RestTemplate restTemplate1;
    @Autowired(required = false)
    DcClient dcClient;
    @Autowired
    ConsumerService consumerService;
    @Autowired(required = false)
    UserInfoRemote userInfoRemote;

    /**
     * 演示客户端, 消费服务端的接口
     * @return
     */
    @GetMapping("/consumer")
    public String dc(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("eureka-client");
        String url = "http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/dc";
        System.out.println(url);
        return restTemplate1.getForObject(url, String.class);
    }

    /**
     * 通过负载均衡, 不用自己显示指定, 获取哪个节点的服务.
     * @return
     */
    @GetMapping("/consumer2")
    public String dc2(){
        return restTemplate.getForObject("http://eureka-client:dc", String.class);
    }

    /**
     * 通过把本地的一个接口伪装成远程服务, 这里再引用.
     *
     * 由于Feign是基于Ribbon实现的, 所以它自带了客户端的负载均衡功能, 也可以通过Ribbon的IRule进行策略扩展,
     * 另外Feign还整合了Hystrix来实现服务的容错保护
     * @return
     */
    @GetMapping("/consumer3")
    public String dc3(){
        return dcClient.consumer();
    }

    /**
     * 测试服务降级功能
     * @return
     */
    @GetMapping("/test1")
    public String test1(){
        return consumerService.consumer();
    }

    /**
     * 测试服务降级功能
     * @return
     */
    @GetMapping("/test2")
    public String test2(){
        return consumerService.consumer();
    }

    /**
     * 这个方法演示, 在调用远程服务时, 虽然是通过接口调用的, 对面接口返回的是一个json的字符串.
     * 但是这里可以直接用对象接收, 但是要注意的一点是该对像必须要有一个无参的构造方法, 因为在进行
     * 对象返回序列化的时候要用到. 这里的序列化可以不用java的Serialize, 因为用的不是java的序列化框架
     *
     * @param id
     * @return
     */
    @RequestMapping("test3/{id}")
    public String test3(@PathVariable Long id){
        TestEntity entity = userInfoRemote.queryById2(id);
        ResponseData responseData = userInfoRemote.queryById3(id, "this is a", new TestEntity(1,"aa",null));
        System.out.println(responseData.toString());
        System.out.println(entity.toString());
        return entity.toString();
    }


}
