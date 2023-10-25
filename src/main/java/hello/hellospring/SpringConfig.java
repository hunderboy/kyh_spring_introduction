package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 자바 코드로 직접 Spring Bean 을 등록하는 방법.
 * 이 방법을 알아야 하는 이유는
 *
 * MemberService(memberRepository());
 * 위와 같이 memberRepository 가 주입되는데
 *
 * 만약에 ConsumerMemberRepository 로 변경 되어야 하는 경우
 * MemberService(ConsumerMemberRepository());
 * 처럼 딱 주입되는 것만 변경하면 되는 상황을 만들수 있다.
 *
 * 상황에 따라 쉽게 대응할수 있는 교체방식을 위한 것이다.
 */
@Configuration
public class SpringConfig {

//    @Bean
//    public MemberService memberService() {
//        return new MemberService(memberRepository());
//    }
//
//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }

}
