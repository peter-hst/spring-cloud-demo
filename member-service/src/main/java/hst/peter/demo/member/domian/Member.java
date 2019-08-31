package hst.peter.demo.member.domian;

import hst.peter.demo.core.domain.Module;
import lombok.*;
import lombok.experimental.Wither;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity(name = "tbl_member")
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Wither
@Data
@EqualsAndHashCode(callSuper = true)
public class Member extends Module {

    @Column(unique = true)
    @Min(value = 10000000000L, message = "手机号不正确")
    @Max(value = 19999999999L, message = "手机号不正确")
    private Long mobile;

    @Column(length = 64)
    private String userName;

    @Column(length = 128, unique = true)
    @Email(message = "不合法的邮箱地址")
    private String email;

    @Column(length = 128)
    private String pwd;

    @Column(length = 64)
    private String nickName;

    @Column(length = 128)
    private String openid;

    @Column(nullable = false)
    private Boolean isActivation;

    @Column(length = 32)
    private String activationCode;

    private String avatar;

}
