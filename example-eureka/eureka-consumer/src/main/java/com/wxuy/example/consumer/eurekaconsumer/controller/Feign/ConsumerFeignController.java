package com.wxuy.example.consumer.eurekaconsumer.controller.Feign;




import com.wxuy.example.consumer.eurekaconsumer.pojo.Demo;
import com.wxuy.example.consumer.eurekaconsumer.service.FeignService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ConsumerFeignController {

	@Autowired
	private FeignService feignService; //FeignService所在的包名需与当前包名在同一个总包下面。。

	@RequestMapping("/eureka/consumer/feign/add")
	public boolean add(Demo demo){
		return feignService.add(demo);
	}

	@GetMapping("/eureka/consumer/feign/{id}")
	public Demo get(@PathVariable("id") Long id){
		return feignService.queryById(id);
	}

	@RequestMapping("/eureka/consumer/feign/list")
	public List<Demo> list(){
		return feignService.queryAll();
	}
}
