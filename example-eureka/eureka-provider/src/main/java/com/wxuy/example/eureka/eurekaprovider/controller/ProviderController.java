package com.wxuy.example.eureka.eurekaprovider.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wxuy.example.consumer.eurekaconsumer.pojo.Demo;
import com.wxuy.example.eureka.eurekaprovider.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProviderController {

	@Autowired
	ProviderService providerService;
	@Autowired
	private DiscoveryClient client;

	@PostMapping("/eureka/provider/add")
	public boolean add(Demo demo) {
		return providerService.add(demo);
	}

	@GetMapping("/eureka/provider/{id}")
	@HystrixCommand(fallbackMethod = "hystrixGet")
	public Demo queryById(@PathVariable("id") Long id) {
		if(id ==10){
			throw new RuntimeException("id => " + id + ",不存在该用户，或者无法找到");
		}
		return providerService.queryById(id);
	}

	@GetMapping("/eureka/provider/list")
	public List<Demo> queryAll() {
		return providerService.queryAll();
	}

	//备选方法(主方法无法调通的时候执行，服务端的熔断机制) 当前通过id为10的时候抛出运行时异常来模拟主方法无法执行的情况。
	public Demo hystrixGet(@PathVariable("id") Long id){
		return new Demo().setDemoID(id)
				.setDemoName("id => " + id + "没有对应的信息，null--@Hystrix")
				.setDb_source("no this database in mysql");
	}

	//注册进来的微服务，获取一些消息
	@GetMapping("/eureka/provider/discovery")
	public Object discovery() {
		//获得微服务列表的清单
		List<String> services = client.getServices();
		System.out.println("discovery=>services:" + services);
		//得到一个具体的微服务信息
		List<ServiceInstance> instances = client.getInstances("EUREKA-PROVIDER-8001");
		for (ServiceInstance instance : instances) {
			System.out.println(
					instance.getHost() + "\t" +
							instance.getPort() + "\t" +
							instance.getUri() + "\t" +
							instance.getServiceId() + "\t"
			);
		}
		return this.client;
	}
}
