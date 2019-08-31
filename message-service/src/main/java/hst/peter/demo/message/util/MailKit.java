package hst.peter.demo.message.util;

import hst.peter.demo.core.util.StrKit;
import hst.peter.demo.core.vo.MailVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

/**
 * @author peter.huang
 * @date 2019/8/31 21:39
 */

@Slf4j
public class MailKit {
    private MailKit() {
    }

    /**
     * 普通邮件投递
     *
     * @param sender
     * @param vo
     * @return
     */
    public static boolean send(JavaMailSender sender, MailVo vo) {
//        if (null == sender || null == vo || !vo.isVerified()) return false;
        SimpleMailMessage message = new SimpleMailMessage();
        BeanUtils.copyProperties(vo, message);
        sender.send(message);
        return true;
    }

    /**
     * HTML格式邮件投递
     *
     * @param sender
     * @param vo
     * @return
     */
    public static boolean sendHtml(JavaMailSender sender, MailVo vo) {
//        if (null == sender || null == vo || !vo.isVerified()) return false;
        MimeMessage message = null;
        message = sender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            setHelper(helper, vo);
            helper.setText(vo.getText(), true);//文本
            sender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            log.error("MAIL SEND ERROR: {}", e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 携带附件的邮件
     *
     * @param sender
     * @param vo
     * @return
     */
    public static boolean sendAttach(JavaMailSender sender, MailVo vo) {
//        if (null == sender || null == vo || !vo.isVerified()) return false;
        MimeMessage message = null;
        message = sender.createMimeMessage();
        try {
            final MimeMessageHelper helper = new MimeMessageHelper(message, true);
            setHelper(helper, vo);
            helper.setText(vo.getText(), true);
            if (null != vo.getAttacheFiles() && !vo.getAttacheFiles().isEmpty() && vo.getAttacheFiles().size() == 0) {
                vo.getAttacheFiles().keySet().forEach(k -> {
                    try {
                        helper.addAttachment(k, vo.getAttacheFiles().get(k));
                    } catch (MessagingException e) {
                        log.error("MAIL ATTACH ADD ERROR: {}", e.getMessage());
                        e.printStackTrace();
                    }
                });
            }
            sender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            log.error("MAIL SEND ERROR: {}", e.getMessage());
            return false;
        }
        return true;
    }

    private static void setHelper(MimeMessageHelper helper, MailVo vo) {
        try {
            if (StrKit.isBlank(vo.getNikename())) {
                helper.setFrom(vo.getFrom());//发件人
            } else {
                helper.setFrom(vo.getFrom(), vo.getNikename());//发件人
            }
            helper.setFrom(vo.getFrom());
            helper.setTo(vo.getTo().toArray(new String[0]));//收件人
            helper.setCc(vo.getCc().toArray(new String[0]));//抄送人
            helper.setBcc(vo.getBcc().toArray(new String[0]));
            helper.setSubject(vo.getSubject());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
