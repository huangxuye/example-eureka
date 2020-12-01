package com.wxuy.example.consumer.eurekaconsumer.service;

import com.wxuy.example.consumer.eurekaconsumer.pojo.Demo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "EUREKA-PROVIDER",fallbackFactory = FeignServiceFallBackFactory.class)
@Component
public interface FeignService {

	@PostMapping("/eureka/provider/add")
	public boolean add(Demo demo);

	@GetMapping("/eureka/provider/{id}")
	public Demo queryById(@PathVariable("id") Long id);

	@GetMapping("/eureka/provider/list")
	public List<Demo> queryAll();
}
