package client.command;

import io.netty.channel.Channel;
import protocol.packet.JoinGroupRequestPacket;
import utils.ConsoleCommand;

import java.util.Scanner;

/**
 * 描述:
 * 群加入命令行
 *
 * @author caorui1
 * @create 2018-11-01 10:12
 */
public class JoinGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        JoinGroupRequestPacket joinGroupRequestPacket = new JoinGroupRequestPacket();

        System.out.print("输入 groupId，加入群聊：");
        String groupId = scanner.next();

        joinGroupRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(joinGroupRequestPacket);
    }
}