package com.wxuy.example.consumer.eurekaconsumer.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Demo implements Serializable {

	private Long DemoID;
	private String DemoName;
	//这个数据存在哪个数据库的字段，微服务，一个服务对应一个数据库，同一个信息可能存在不同的数据库
	private String db_source;
}
