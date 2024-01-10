package jpabook.jpashopstudy.service;

import static org.junit.Assert.*;

import jpabook.jpashopstudy.domain.Member;
import jpabook.jpashopstudy.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    /*
    회원가입 테스트 코드 작성
     */
    @Test
    void 회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setName("kim");
        // when
        Long saveId = memberService.join(member);

        // then

        assertEquals(member, memberRepository.findOne(saveId));
    }

    /*
    회원 중복 테스트 코드 작성
     */
    @Test
    void 중복_회원_예외() throws Exception {
        // given
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("kim");
        member2.setName("kim");
        // when
        memberService.join(member1);

        // then

        assertThrows(IllegalStateException.class, ()-> {
            memberService.join(member2);
        });
    }
}