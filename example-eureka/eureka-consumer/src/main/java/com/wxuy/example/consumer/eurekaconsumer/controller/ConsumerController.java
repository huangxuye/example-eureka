package com.wxuy.example.consumer.eurekaconsumer.controller;

import com.wxuy.example.consumer.eurekaconsumer.pojo.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ConsumerController {

	@Autowired
	private RestTemplate restTemplate;

	//Ribbon,这里的地址应该是一个变量，通过服务名来访问 社区版负载均衡Feign 通过接口的形式来访问
	//private static final String REST_URL_PREFIX = "http://localhost:8001";
	private static final String REST_URL_PREFIX = "http://EUREKA-PROVIDER";

	@PostMapping("/eureka/consumer/add")
	public boolean add(Demo demo) {
		return restTemplate.getForObject(REST_URL_PREFIX + "/eureka/provider/add",Boolean.class);
	}

	@GetMapping("/eureka/consumer/{id}")
	public Demo queryById(@PathVariable("id") Long id) {
		return restTemplate.getForObject(REST_URL_PREFIX + "/eureka/provider/"+id,Demo.class);
	}

	@GetMapping("/eureka/consumer/list")
	public List<Demo> queryAll() {
		return restTemplate.getForObject(REST_URL_PREFIX + "/eureka/provider/list",List.class);
	}



}
