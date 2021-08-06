package net.xdclass.online_xdclass.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @description:Json对象，用于前后端的数据交互
 * @author: Felix_XHF
 * @create:2021-07-29 20:42
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JsonData {
    //状态码 0表示成功，1表示处理中 -1表示失败
    private Integer code;

    //业务数据
    private Object data;

    //信息描述
    private String msg;


    /*
    * @Description: 成功，不用返回数据
    * @Author: Mr.Felix
    * @Time: 2021/7/29
    **/
    public static JsonData buildSuccess(){
        return new JsonData(0,null,null);
    }

    /*
    * @Description: 成功，返回data
    * @Author: Mr.Felix
    * @Time: 2021/7/29
    **/
    public static JsonData buildSuccess(Object data){
        return new JsonData(0,data,null);
    }

    /*
    * @Description: 失败，错误信息
    * @Author: Mr.Felix
    * @Time: 2021/7/29
    **/
    public static JsonData buildError(String msg){
        return new JsonData(-1,null,msg);
    }

    /*
    * @Description: 失败，自定义状态码
    * @Author: Mr.Felix
    * @Time: 2021/7/29
    **/
    public static JsonData buildError(Integer code,String msg){
        return new JsonData(code,null,msg);
    }
}
