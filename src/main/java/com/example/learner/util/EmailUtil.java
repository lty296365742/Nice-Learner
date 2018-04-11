package com.example.learner.util;


import io.github.isliqian.AnnNiceConfig;
import io.github.isliqian.NiceEmail;
import io.github.isliqian.VerificationCode;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by LiQian_Nice on 2018/3/24
 */
@AnnNiceConfig(type = "SMTP_QQ",username = "51103942@qq.com",password = "jtmoybnwknrnbjha")
public class EmailUtil {

    private static Logger logger= LoggerFactory.getLogger(EmailUtil.class);
    /**
     * 发送邮件的方法
     */
    @Test
    public static String  sendMail(String to) throws Exception {
        NiceEmail.inUse(EmailUtil.class)
                .subject("测试")
                .from("LqNice")
                .to(to)
                .verificationCode(6, VerificationCode.verificationCodeArrary)
                .send();
        logger.info("发送成功");
        logger.info(NiceEmail.code);
        return NiceEmail.code;
    }
}
