package com.project.store.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.store.entity.User;
import com.project.store.mapper.UserMapper;
import com.project.store.service.UserService;
import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;
import java.security.GeneralSecurityException;



/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2021-11-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean sendNotification(String email) {
        String mess = "亲，你的商品被拍下，请及时上号处理";
        return sendEmail(email, mess);
    }

    @Override
    public String sendVerification(String email) {
        Random random = new Random();
        int num = random.nextInt(1000000);
        String ver = String.valueOf(num);
        String mess = "本次验证码为：" + ver;
        sendEmail(email, mess);
        return ver;
    }

    @Override
    public boolean sendEmail(String email, String text) {
        String to = email;
        String from = "457894974@qq.com";
        String host = "smtp.qq.com";

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        assert sf != null;
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("457894974@qq.com", "qvsbwuuaunohbiib"); //发件人邮件用户名、密码
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.setSubject("SUSTech Store");
            message.setText(text);

            Transport.send(message);
            System.out.println("Sent message successfully....");
            return true;

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }


        return false;
    }

    @Override
    public boolean pay(Integer buyerId, Float price) {
        User buyer = userMapper.selectById(buyerId);
        if (buyer.getBalance() - price < 0) {
            return false;
        }
        buyer.setBalance(buyer.getBalance() - price);
        userMapper.updateById(buyer);
        return true;
    }
}
