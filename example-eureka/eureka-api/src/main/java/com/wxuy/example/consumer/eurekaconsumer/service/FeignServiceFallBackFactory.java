package com.wxuy.example.consumer.eurekaconsumer.service;

import com.wxuy.example.consumer.eurekaconsumer.pojo.Demo;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FeignServiceFallBackFactory implements FallbackFactory {
	public FeignService create(Throwable throwable) {
		return new FeignService() {
			public Demo queryById(Long id) {
				return new Demo()
						.setDemoID(id)
						.setDemoName("id => " + id + "没有对应的信息，客服端提供了降级的信息，这个服务现在已经被关闭")
						.setDb_source("没有数据");
			}

			public List<Demo> queryAll() {
				return null;
			}

			public boolean add(Demo dept) {
				return false;
			}
		};
	}
}
