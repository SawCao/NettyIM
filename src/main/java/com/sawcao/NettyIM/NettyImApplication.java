package com.sawcao.NettyIM;

import io.netty.channel.ChannelFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import server.NettyServer;

import java.net.InetSocketAddress;

@SpringBootApplication
@EnableAutoConfiguration
public class NettyImApplication {

	public static void main(String[] args) {
		SpringApplication.run(NettyImApplication.class, args);
	}
}
