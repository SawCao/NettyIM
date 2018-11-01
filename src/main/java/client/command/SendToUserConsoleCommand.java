package client.command;

import io.netty.channel.Channel;
import protocol.packet.MessageRequestPacket;
import utils.ConsoleCommand;

import java.util.Scanner;

/**
 * 描述:
 * 发送消息
 *
 * @author caorui1
 * @create 2018-10-31 18:02
 */
public class SendToUserConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("发送消息给某个某个用户：");
        String toUserId = scanner.next();
        String message = scanner.next();
        channel.writeAndFlush(new MessageRequestPacket(toUserId, message));
    }
}
