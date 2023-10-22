package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
    // Controller 가 우선이다.
    // home 화면 작성시, 아래 처럼 getMapping("/") 하여, 먼저 컨트롤러 단에서 우선순위가 설정되게 끔 한다.
    @GetMapping("/") // localhost:8080 호출 시 index.html 이 불러지는 게 아닌 home.html 을 호출 하게 된다.
    public String home() {
        return "home";
    }
}