package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test 의 핵심은 정상처리확인이 아니라, 예외가 잘 처리가 되는지가 핵심이다.
 */
class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;


    // 동작 하기전에 실행 되는 코드
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository); // memberRepository 를 주입
    }

    // 동작이 끝난 뒤에 실행 되는 코드
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore(); // 테스트가 끝날때마다 데이터 Clear
    }

    @Test
    void 회원가입() {
        /**
         문법
         given : 뭔가 주어짐
         when : 이런 상황 일때,
         then : 어떻게 나오나?
         */
        //Given
        Member member = new Member();
        member.setName("hello");
        //When
        Long saveId = memberService.join(member);
        //Then
//        Member findMember = memberService.findOne(saveId).get();
        Member findMember = memberRepository.findById(saveId).get(); // 무조건 Optional 에 값이 있을 거라 판단하고 .get() 사용한 것
        assertEquals(member.getName(), findMember.getName());

        /**
         * .get()은 Java의 Optional 클래스에서 사용되는 메서드입니다.
         * 이 메서드는 Optional 객체에서 값을 추출하거나 Optional이 비어있지 않을 때 해당 값을 반환하는 데 사용됩니다.
         * 여기서 코드에서 memberRepository.findById(saveId)는 아마도 saveId를 사용하여 데이터베이스에서 회원을 찾는 메서드로 보입니다.
         * 이 메서드는 Optional<Member>를 반환할 것이며, .get() 메서드를 사용하여 해당 Optional 객체에서 Member 객체를 추출하고 반환합니다.
         *
         * 주의할 점은, 만약 Optional이 비어있는 상태에서 .get()을 호출하면 NoSuchElementException이 발생할 수 있으므로,
         * 항상 해당 Optional이 비어있지 않은 경우에만 .get()을 사용해야 합니다.
         * 이것은 Optional을 사용하는 주요 이점 중 하나로서, NullPointerException을 방지하고 안전한 코드를 작성하는 데 도움이 됩니다.
         *
         * 만약 값이 없을 때 어떻게 처리할지에 대한 대안적인 처리가 필요한 경우,
         * .isPresent()나 .orElse()와 같은 메서드를 사용할 수 있습니다.
         */
    }


    /**
     * Test 의 핵심은 정상처리확인이 아니라, 예외가 잘 처리가 되는지가 핵심이다.
     */
    @Test
    public void 중복_회원_예외() throws Exception {

        //Given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //When
        memberService.join(member1);
        IllegalStateException e = assertThrows(
                IllegalStateException.class,        // IllegalStateException 예외가 발생해야 한다.
                () -> memberService.join(member2)   // 이 코드를 실행 시켰을때
        );

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }

}