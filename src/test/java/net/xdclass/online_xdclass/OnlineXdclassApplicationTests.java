package net.xdclass.online_xdclass;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.lang.Assert;
import net.xdclass.online_xdclass.model.entity.User;
import net.xdclass.online_xdclass.utils.JWTUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OnlineXdclassApplicationTests {

    @Test
    public void testGeneJwt(){
        User user = new User();
        user.setId(66);
        user.setName("二当家");
        user.setHeadImg("png");
        String token = JWTUtils.geneJsonWebToken(user);
        System.out.println(token);

        Claims claims = JWTUtils.checkJWT(token);
        System.out.println(claims.get("name"));


//        Assert.isTrue();
    }

}
