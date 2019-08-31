package hst.peter.demo.member.controller;

import hst.peter.demo.core.controller.CrudController;
import hst.peter.demo.core.util.StrKit;
import hst.peter.demo.member.domian.Member;
import hst.peter.demo.member.repository.MemberRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController extends CrudController<MemberRepository, Member> {

    private MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository) {
        super(memberRepository);
        this.memberRepository = memberRepository;
    }

    @Override
    protected Member saveOrUpdateBefore(Member member) {
        if (null == member.getId())
            return member
                    .withActivationCode(StrKit.uuid())
                    .withIsActivation(false);
        return member;
    }

}
