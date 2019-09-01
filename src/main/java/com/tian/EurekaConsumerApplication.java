package com.tian;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.dbcp.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;


/**
 * @EnableFeignClients 注解开启spring cloud feign功能
 *
 * @EnableCircuitBreaker 注解用来开启服务的降级保护等功能
 *
 * @EnableHystrix 也可以用来开启服务降级保护等功能
 *
 */
@MapperScan("com.tian.dao")
@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class EurekaConsumerApplication {

	/**
	 * 通过添加@LoadBalanced注解, 实现自动负载均衡
	 * @return
	 */
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public DataSource dataSource(@Value("${spring.datasource.username}") String userName,
								 @Value("${spring.datasource.password}") String password,
								 @Value("${spring.datasource.url}") String url,
								 @Value("com.mysql.jdbc.Driver") String driverClassName){
		BasicDataSource dataSource =  new BasicDataSource();
		dataSource.setUsername(userName);
		dataSource.setPassword(password);
		dataSource.setUrl(url);
		dataSource.setDriverClassName(driverClassName);

		return dataSource;
    }

	@Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

	@Bean
	public ActiveMQConnectionFactory activeMQConnectionFactory(){
		return new ActiveMQConnectionFactory();
	}

	public static void main(String[] args) {
		SpringApplication.run(EurekaConsumerApplication.class, args);
	}

}


