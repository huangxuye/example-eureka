package com.wxuy.example.eureka.eurekaprovider.service;


import com.wxuy.example.consumer.eurekaconsumer.pojo.Demo;

import java.util.List;

public interface ProviderService {

	boolean add(Demo dept);

	Demo queryById(Long id);

	 List<Demo> queryAll();

}
