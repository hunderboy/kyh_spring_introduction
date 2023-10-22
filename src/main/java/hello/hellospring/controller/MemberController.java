package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    // MemberService 는 여러개를 만들 필요가 없다.
    // MemberService 는 다른 Controller 에서도 공용으로 사용할수 있다.
    private final MemberService memberService;


    @Autowired // MemberController 와 memberService 간에 연결을 생성과 동시에 시켜준다.
    public MemberController(MemberService memberService) { // 생성자 파라머터로 설정(의존성 주입)
        this.memberService = memberService;
    }


    @GetMapping(value = "/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }


    @PostMapping(value = "/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member = " + member.getName());

        memberService.join(member); // 회원 가입 처리

        return "redirect:/"; // 회원 가입에 성공하면 home 화면으로 보내겠다는 의미
    }



    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }



}
