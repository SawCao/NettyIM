package client.command;

import io.netty.channel.Channel;
import protocol.packet.LoginRequestPacket;
import utils.ConsoleCommand;

import java.util.Scanner;

/**
 * 描述:
 * 登录命令
 *
 * @author caorui1
 * @create 2018-10-31 18:01
 */
public class LoginConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        System.out.print("输入用户名登录: ");
        loginRequestPacket.setUserName(scanner.nextLine());
        loginRequestPacket.setPassword("pwd");

        // 发送登录数据包
        channel.writeAndFlush(loginRequestPacket);
        waitForLoginResponse();
    }

    private static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }
}
