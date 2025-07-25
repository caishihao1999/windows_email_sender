import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {
    public static void main(String[] args) {
        // QQ邮箱配置（全部写死，无需参数）
        String smtpServer = "smtp.qq.com";
        int port = 587;
        String fromAddress = "1292769254@qq.com";
        String accessToken = "vrddiategywofjeb"; // 请替换为你的真实授权码
        String toAddress = "1292769254@qq.com";
        String subject = "有人登录该电脑";
        String body = "有人登录该电脑";

        // 配置SMTP服务器属性
        Properties properties = new Properties();
        properties.put("mail.smtp.host", smtpServer);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", smtpServer);

        // 创建认证器
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromAddress, accessToken);
            }
        };

        // 创建会话
        Session session = Session.getInstance(properties, authenticator);
        session.setDebug(true); // 开启调试模式

        try {
            // 创建邮件消息
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromAddress));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
            message.setSubject(subject);
            message.setText(body);

            // 发送邮件
            Transport.send(message);
            System.out.println("邮件发送成功");
        } catch (MessagingException e) {
            System.err.println("邮件发送失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
}    