package client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import protocol.code.PacketDecoder;
import protocol.code.PacketEncoder;
import protocol.packet.LoginRequestPacket;
import protocol.packet.MessageRequestPacket;
import utils.LoginUtil;
import utils.SessionUtil;
import utils.Spliter;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * 描述:
 * 客户端
 *
 * @author caorui1
 * @create 2018-10-24 11:56
 */
public class NettyClient {
    /**
     * 最大重试数
     */
    private static int MAX_RETRY = 6;

    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                // 1.指定线程模型
                .group(workerGroup)
                // 2.指定 IO 类型为 NIO
                .channel(NioSocketChannel.class)
                // 3.IO 处理逻辑
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(ClientLoginHandler.INSTANCE);
                        ch.pipeline().addLast(ClientMessageHandler.INSTANCE);
                        ch.pipeline().addLast(new PacketEncoder());
                    }
                });
        // 4.建立连接
        failedCountConnect(bootstrap, "127.0.0.1", 8000, MAX_RETRY);
    }

    /**
     * @return void
     * @author caorui
     * @description: 简单的失败重试
     */
    private static void simpleConnect(Bootstrap bootstrap, String host, int port) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.print("连接成功！");
            } else {
                System.out.print("连接失败！");
                simpleConnect(bootstrap, host, port);
            }
        });
    }

    /**
     * @return void
     * @author caorui
     * @description: 带有重试次数及延迟测试的重试
     */
    private static void failedCountConnect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.print("连接成功！");
                Channel channel = ((ChannelFuture)future).channel();
                startConsoleThread(channel);
            } else if (retry == 0) {
                System.out.print("重试次数已经用完！");
            } else {
                int order = (MAX_RETRY - retry) + 1;
                int delay = 1 << order;
                System.err.print(new Date() + ":连接失败！第" + order + "次重连！");
                bootstrap.config().group().schedule(() -> failedCountConnect(bootstrap, host, port, retry - 1), delay, TimeUnit
                        .SECONDS);
            }
        });
    }

    private static void startConsoleThread(Channel channel) {
        Scanner sc = new Scanner(System.in);
        new Thread(() -> {
            while (!Thread.interrupted()){
                if(!SessionUtil.hasLogin(channel)){
                    System.out.print("请输入用户名：");
                    //读取控制台输入
                    String line = sc.nextLine();
                    //打包
                    LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
                    loginRequestPacket.setUserName(line);
                    //设置默认密码
                    loginRequestPacket.setPassword("pwd");
                    //发送
                    channel.writeAndFlush(loginRequestPacket);
                    //线程暂停一秒，等待接收登录消息
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.print("输入你想要发送对象的名字：");
                    String toId = sc.next();
                    System.out.print("输入你想要发送的消息：");
                    String msg = sc.next();
                    channel.writeAndFlush(new MessageRequestPacket(toId,msg));
                }

            }
        }).start();
    }


}
