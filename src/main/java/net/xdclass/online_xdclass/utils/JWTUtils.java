package net.xdclass.online_xdclass.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.xdclass.online_xdclass.model.entity.User;

import java.util.Date;

/*
 * @description:JWT Json Web Token 解决跨域身份验证问题
 * 注意点：
 *  1. 生成的token是可以通过base64进行解密出明文信息的
 *  2. base64进行解密出明文信息，修改在进行编码，则会解密失败
 *  3. 无法作废已经颁布的令牌token，除非改秘钥
 * @author: Felix_XHF
 * @create:2021-07-29 23:20
 */
public class JWTUtils {
    //失效时间,一周
    private static final long EXPIRE = 60000 * 60 * 24 * 7;

    //加密秘钥
    private static final String SECRET = "xdclass.net168";

    //令牌前缀
    private static final String TOKEN_PREFIX = "xdclass";

    //颁布者
    private static final String SUBJECT = "xdclass";

    /*
    * @Description: 根据用户信息生成令牌 token
    * @Author: Mr.Felix
    * @Time: 2021/7/29
    **/
    public static String geneJsonWebToken(User user){
        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("head_img",user.getHeadImg())
                .claim("id",user.getId())
                .claim("name",user.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() +
                        EXPIRE))
                .signWith(SignatureAlgorithm.HS256,SECRET).compact();
        token = TOKEN_PREFIX + token;
        return token;
    }

    /*
    * @Description: 校验用户的token是否有效
    * @Author: Mr.Felix
    * @Time: 2021/7/29
    **/
    public static Claims checkJWT(String token){
        try {
            final Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
            return claims;
        }catch (Exception e){
            return null;
        }
    }
}
