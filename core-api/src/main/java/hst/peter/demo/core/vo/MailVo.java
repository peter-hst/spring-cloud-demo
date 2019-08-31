package hst.peter.demo.core.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.Resource;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 *
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MailVo implements Serializable {

    private String from; //发件人

    private String nikename; //显示名

    @NotEmpty(message = "填写收件人")
    private List<String> to; //收件人

    private List<String> cc; // 抄送人

    private List<String> bcc;

    @NotNull(message = "填写邮件主题")
    private String subject;

    @NotNull(message = "填写邮件内容")
    private String text;

//    private String strTo;
//
//    private String strCc;
//
//    private String strBcc;

    private HashMap<String, Resource> attacheFiles; //附件

//    private HashMap<String, Resource> attacheFiles; //附件


    public void setTo(List<String> to) {
        this.to = to;
    }

    public void setCc(List<String> cc) {
        this.cc = cc;
    }

    public void setBcc(List<String> bcc) {
        this.bcc = bcc;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setAttacheFiles(HashMap<String, Resource> attacheFiles) {
        this.attacheFiles = attacheFiles;
    }
}