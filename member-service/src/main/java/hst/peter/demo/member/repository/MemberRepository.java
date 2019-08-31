package hst.peter.demo.member.repository;

import hst.peter.demo.core.repository.BaseRepository;
import hst.peter.demo.member.domian.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends BaseRepository<Member> {
    Member findByOpenid(String openid);

    Member findByNickName(String nickName);

    Member findByEmail(String email);
}
