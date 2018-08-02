package com.tian.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2018/7/31 0031.
 */
@Service
public class ConsumerService {
    @Autowired
    RestTemplate restTemplate;

    /**
     * @HystrixCommand 注解用于标识, 服务降级方法
     * fallbackMethod 属性, 用于指定了一个方法名, 当响应过忙的时候, 不再调用原方法, 而是调用这里指定的方法.
     *
     * 该注解同时也实现了服务隔离功能.
     * Hystrix的服务隔离是基于线程池的隔离实现的. 它会为每一个Hystrix命令创建一个独立的线程池, 这样就算某个
     * Hytrix命令包装下的服务出现了延迟过高, 也只会对该服务产生影响, 不会影响其它的服务.
     *
     * 因为主方法, 和降级方法处理的时机一致, 所以返回类型也要保持一致.
     * 第二个需要注意的是, 当出现服务降级或者服务熔断的时候, 降级方法并不是替换整个主方法, 而只是替换了restTemplate
     * 的调用远程方法.
     *
     * 在已经发生了服务降级的情况下, 爱限于超时的问题, 仍然有可能产生请求堆积, 这个时候断路器就会产生作用了,
     * 断路器有三个比较重要的参数如下:
     * 快照时间窗: 断路器确定是否打开需要统计一些请求和错误数据, 而统计的时间范围就是时间窗, 默认为最近10秒.
     * 请求总数下限: 在快照时间窗内, 必须满足请求总数下限才有资格进行熔断. 默认为20, 这意味着, 如果10秒内,
     *               总的调用次数不到20次, 即使所有的请求都超时, 或者其它原因失败, 也不会打开断路器.
     * 错误百分比下限: 当请求总次数, 在快照时间内超过了设制的下限. 如果失败率超过50%(默认), 这时就会自动打开
     *                 断路器.
     *
     * 在断路器打开之后，处理逻辑并没有结束，我们的降级逻辑已经被成了主逻辑，那么原来的主逻辑要如何恢复呢？对于这一问题，
     * hystrix也为我们实现了自动恢复功能。当断路器打开，对主逻辑进行熔断之后，hystrix会启动一个休眠时间窗，在这个时间窗
     * 内，降级逻辑是临时的成为主逻辑，当休眠时间窗到期，断路器将进入半开状态，释放一次请求到原来的主逻辑上，如果此次请
     * 求正常返回，那么断路器将继续闭合，主逻辑恢复，如果这次请求依然有问题，断路器继续进入打开状态，休眠时间窗重新计时。
     *
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallback")
    public String consumer() {
        String result = restTemplate.getForObject("http://eureka-client/dc", String.class);
        System.out.println("result = "+result);
        System.out.println("---------服务降级, 我也会打印.");
        return result;
    }

    public String fallback() {
        return "this is fallback method response.";
    }

    @HystrixCommand(fallbackMethod = "fallback2")
    public String consumer2(){
        String result = restTemplate.getForObject("http://eureka-client/dc", String.class);
        System.out.println("result = "+result);
        System.out.println("---------服务降级, 我也会打印.");
        return result;
    }

    public String fallback2() {
        return "this is fallback2 method response.";
    }

}
