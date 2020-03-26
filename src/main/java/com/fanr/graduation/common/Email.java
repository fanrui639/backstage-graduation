package com.fanr.graduation.common;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.security.GeneralSecurityException;
import java.util.Properties;

public class Email{

    //用于给用户发送邮件的邮箱
    private static String from = "fanr639@qq.com";
    //邮箱的用户名
    private static String username = "fanr639@qq.com";
    //邮箱的密码
    private static String password = "jydijzclezwcecja";
    //发送邮件的服务器地址
    private static String host = "smtp.qq.com";


    //发送邮件
    public static void sendEmail(String title,String msg, String account) throws GeneralSecurityException, MessagingException {

        Properties prop = new Properties();
        prop.setProperty("mail.host", host); //// 设置QQ邮件服务器
        prop.setProperty("mail.transport.protocol", "smtp"); // 邮件发送协议
        prop.setProperty("mail.smtp.auth", "true"); // 需要验证用户名密码

        // 关于QQ邮箱，还要设置SSL加密，加上以下代码即可
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);

        //使用JavaMail发送邮件的5个步骤

        //创建定义整个应用程序所需的环境信息的 Session 对象

        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                //发件人邮件用户名、授权码
                return new PasswordAuthentication(username, password);
            }
        });


        //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
//        session.setDebug(true);

        //2、通过session得到transport对象
        Transport ts = session.getTransport();

        //3、使用邮箱的用户名和授权码连上邮件服务器
        ts.connect(host, username, password);

        //4、创建邮件

        //创建邮件对象
        MimeMessage message = new MimeMessage(session);

        //指明邮件的发件人
        message.setFrom(new InternetAddress(username));

        //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发     收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(username));

        //邮件的标题
        message.setSubject(title);

        //邮件的文本内容
        message.setContent(msg + "\n来自用户:" + account, "text/html;charset=UTF-8");

        //5、发送邮件
        ts.sendMessage(message, message.getAllRecipients());

        ts.close();

    }


//    //重写run方法的实现，在run方法中发送邮件给指定的用户
//    @Override
//    public void run() {
//        try{
//
//        }catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}
