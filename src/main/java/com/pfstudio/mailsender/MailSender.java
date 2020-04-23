package com.pfstudio.mailsender;

import com.pfstudio.mailsender.pojo.UserInfo;
import com.sun.mail.util.MailSSLSocketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * @author 杨丰畅
 * @description 发送邮件助手类
 * @date 2020/4/22 17:00
 **/
public class MailSender {

    public static final Logger logger = LoggerFactory.getLogger(MailSender.class);

    /**
     * @description 发送邮件
     * @param userInfo
     */
    public static void send(UserInfo userInfo) {
        Properties props = new Properties();
        // 开启debug调试
        props.setProperty("mail.debug", "true");
        // 发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");
        // 设置邮件服务器主机名
        props.setProperty("mail.host", "smtp.qq.com");
        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");

        // 开启SSL加密，否则会失败
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.smtp.ssl.socketFactory", sf);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

        // 创建session
        Session session = Session.getInstance(props);

        // 创建邮件
        Message msg = new MimeMessage(session);

        // 设置标题和内容
        try {
            msg.setSubject(SettingReader.getMailTitle());
            String unFormatedContent = SettingReader.getMailContent();
            String content = String.format(unFormatedContent,
                    userInfo.getStuNumber(),
                    userInfo.getName(),
                    userInfo.getAccount(),
                    userInfo.getPassword()
            );
            msg.setText(content);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        // 设置发送人地址
        try {
            msg.setFrom(new InternetAddress(SettingReader.getEmailSender()));
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        Transport transport = null;
        try {
            transport = session.getTransport();
            transport.connect("smtp.qq.com", SettingReader.getEmailSender(),
                    SettingReader.getGrantCode());
            transport.sendMessage(msg, new Address[]{new InternetAddress(userInfo.getEmail())});
            logger.info("给 {} 的邮件发送成功", userInfo.getName());
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
            logger.warn("给 [{}] 的邮件发送失败，该用户账号为 [{}], 密码为 [{}], 专业为 [{}], 学号为 [{}]",
                    userInfo.getName(),
                    userInfo.getAccount(),
                    userInfo.getPassword(),
                    userInfo.getMajor(),
                    userInfo.getStuNumber());
        }
    }
}