package com.sawcao.NettyIM;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.sawcao.NettyIM.server.NettyServer;

@SpringBootApplication
@EnableAutoConfiguration
public class NettyImApplication {

	public static void main(String[] args) {
		SpringApplication.run(NettyImApplication.class, args);
	}
}
