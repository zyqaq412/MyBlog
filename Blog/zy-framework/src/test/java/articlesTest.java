import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @title: articlesTest
 * @Author zxwyhzy
 * @Date: 2022/12/16 15:39
 * @Version 1.0
 */

public class articlesTest {
    @Test
    public void testBCryptPasswordEncoder(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("test");
        System.out.println(encode);//$2a$10$thltlvKtDb4SAth6C5z3E.zPmSDYRp4LNXb1R0aJ15L.iyFlPMK3i
        System.out.println(passwordEncoder
                .matches("123",
                        "$2a$10$thltlvKtDb4SAth6C5z3E.zPmSDYRp4LNXb1R0aJ15L.iyFlPMK3i"));
    }
}
