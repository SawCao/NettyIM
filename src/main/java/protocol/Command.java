package protocol;

import io.netty.buffer.ByteBuf;

/**
 * Created by IntelliJ IDEA.
 * User: caorui
 * Time: 2018/10/24
 **/
public interface Command{
    Byte LOGIN_REQUEST = 1;
    Byte LOGIN_RESPONSE = 2;
    Byte MESSAGE_REQUEST = 3;
    Byte MESSAGE_RESPONSE = 4;
    Byte CREATE_GROUP_REQUEST = 5;
    Byte CREATE_GROUP_RESPONSE = 6;
    Byte QUIT_GROUP_RESPONSE = 7;
    Byte QUIT_GROUP_REQUEST = 8;
    Byte LIST_GROUP_MEMBERS_RESPONSE = 9;
    Byte LIST_GROUP_MEMBERS_REQUEST = 10;
    Byte JOIN_GROUP_RESPONSE = 11;
    Byte JOIN_GROUP_REQUEST = 12;
    Byte GROUP_MESSAGE_REQUEST = 13;
    Byte GROUP_MESSAGE_RESPONSE = 14;

}
