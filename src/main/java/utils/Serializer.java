package utils;


/**
 * Created by IntelliJ IDEA.
 * User: caorui
 * Time: 2018/10/24
 **/
public interface Serializer {
    /**
     * json 序列化
     */
    byte JSON_SERIALIZER = 1;

    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     */
    byte getSerializerAlgorithm();

    /**
     * 转换为二进制
     */
    byte[] serialize(Object object);

    /**
     * 转换为bean
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);

}
