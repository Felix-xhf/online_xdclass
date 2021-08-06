package net.xdclass.online_xdclass.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @description:错误页面定制
 * @author: Felix_XHF
 * @create:2021-07-29 21:55
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class XDException extends RuntimeException{
    private Integer code;
    private String msg;
}
