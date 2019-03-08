package utils;


import com.sawcao.beanoperation.BeanHelper;

/**
 * Created by IntelliJ IDEA.
 * User: caorui
 * Time: 2018/10/24
 **/
public class JSONSerializer implements Serializer {
    BeanHelper beanHelper = new BeanHelper();

    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    public byte[] serialize(Object object) {
        return beanHelper.beanToJsonBytes(object);
    }

    public Object deserialize(Class bean, byte[] bytes) {
        return beanHelper.jsonBytesToBean(bytes, bean);
    }
}
