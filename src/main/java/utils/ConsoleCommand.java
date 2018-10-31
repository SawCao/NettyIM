package utils;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 描述:
 * 命令行操作
 *
 * @author caorui1
 * @create 2018-10-31 16:03
 */
public interface ConsoleCommand {
    void exec(Scanner scanner, Channel channel);
}
