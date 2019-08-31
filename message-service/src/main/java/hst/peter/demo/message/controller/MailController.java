package hst.peter.demo.message.controller;

import hst.peter.demo.core.vo.MailVo;
import hst.peter.demo.core.vo.Result;
import hst.peter.demo.message.util.MailKit;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author peter.huang
 * @date 2019/8/31 17:28
 */

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Value("${spring.mail.nikename}")
    private String nikename;

    /**
     * 普通文本格式邮件的投递
     *
     * @return
     */
    @PostMapping("/send")
    public Result send(@RequestBody MailVo mailVo) {
        val rs = MailKit.send(mailSender, MailVo.builder().nikename(nikename).from(sender).to(mailVo.getTo()).cc(mailVo.getCc()).bcc(mailVo.getBcc()).subject(mailVo.getSubject()).text(mailVo.getText()).build());
        return rs ? Result.ok(null, "MAIL SENT DONE") : Result.fail("MAIL SEND ERROR");
    }

    /**
     * Html格式的邮件投递
     *
     * @return
     */
    @PostMapping("/sendHtml")
    public Result sendHtml(@RequestBody MailVo mailVo) {
        val rs = MailKit.sendHtml(mailSender, MailVo.builder().from(sender).to(mailVo.getTo()).cc(mailVo.getCc()).bcc(mailVo.getBcc()).subject(mailVo.getSubject()).text(mailVo.getText()).build());
        return rs ? Result.ok(null, "MAIL SENT DONE") : Result.fail("MAIL SEND ERROR");
    }
}
