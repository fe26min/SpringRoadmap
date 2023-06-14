package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//JPA가 조회하는 곳에서 성능을 빠르게 해줍니다.
@Transactional(readOnly = true)
// 모든 필드를 생성자 주입으로 주입
//@AllArgsConstructor

// final 필드만 생성자 주입으로 주입
@RequiredArgsConstructor
public class MemberService {
    
    // 필드 인젝션
    // 테스트를 하거나 그럴때 바꿔줄 수 없다.
//    @Autowired
//    private MemberRepository memberRepository;

    // 세터 인젝션
    // 이제 바꿔줄 수 있다.
    // 그러나 언제 바뀌는지 누가 바꾸는지를 보기 힘들다.
//    private MemberRepository memberRepository;
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository){
//        this.memberRepository = memberRepository;
//    }

    // 생성자 인젝션
    // 최초 생성 될때만 바뀝니다.
    // 한 번만 주고 더 이상 변경되지 않는다.
    
    // final을 통해 실수를 방지
    private final MemberRepository memberRepository;
    // 오토와이어드 생략 가능
//    @Autowired
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    // 회원 가입
    // 읽기 전용이 아니다.
    // 데이터 변경이 되지 않는다.
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }
    
    // 중복 확인
    private void validateDuplicateMember(Member member) {
        // 같은 이름들이 있는지를 찾습니다.
        // 동시에 호출되면 문제가 발생한다.
        // 실무에서는 이걸 방지해야 합니다.
        List<Member> findMembers = memberRepository.findByName(member.getName());

        // 찾았다면 이미 회원이 있다.
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
