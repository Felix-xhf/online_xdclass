package net.xdclass.online_xdclass.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @description:登录 request
 * @author: Felix_XHF
 * @create:2021-07-29 23:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    private String phone;
    private String pwd;
}
