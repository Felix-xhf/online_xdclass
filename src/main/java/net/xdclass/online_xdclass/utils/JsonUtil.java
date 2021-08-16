package net.xdclass.online_xdclass.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * @description:
 * @author: Felix_XHF
 * @create:2021-08-11 15:58
 */
public class JsonUtil {
    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();
    /**
     * 将对象转换成json字符串。
     */
    public static String objectToJson(Object data) {
        try {
            String string = MAPPER.writeValueAsString(data);
            return string;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 将json结果集转化为对象
     *
     * @param jsonData json数据
     */
    public static <T> T jsonToPojo(String
                                           jsonData, Class<T> beanType) {
        try {
            T t = MAPPER.readValue(jsonData,
                    beanType);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
