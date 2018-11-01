package client.command;

import io.netty.channel.Channel;
import utils.ConsoleCommand;

import java.util.Scanner;

/**
 * 描述:
 * 登出命令
 *
 * @author caorui1
 * @create 2018-10-31 18:02
 */
public class LogoutConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
       /* LogoutRequestPacket logoutRequestPacket = new LogoutRequestPacket();
        channel.writeAndFlush(logoutRequestPacket);*/
    }
}
