package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // get 방식
    @GetMapping("hello") // Get 요청 = localhost:8080/hello
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello"; // templates Mapping = templates 폴더의 hello 명을 가진 View 로드한다. - 서버사이드 랜더링 방식
    }

    // get 방식 + 파라미터
    @GetMapping("hello-mvc")
    public String helloMvc(
            @RequestParam("name") String name, // @RequestParam("name") = name 이라는 key 데이터를 get방식으로 받겠다.
            Model model
    ) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    // API 방식 - String 반환
    @GetMapping("hello-string") // Get 메소드 url
    @ResponseBody // http 의 body
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;  // 반환값이 Text 인 경우,템플릿의 html 코드가 아닌 있는 그래도 Text 배출
    }

    // API 방식 - 객체 반환 = 실제로 제일 많이 쓰게될 방식
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello(); // command + shift + enter
        hello.setName(name);
        return hello; // @ResponseBody 를 사용하고, 반환값이 객체인 경우, JSON으로 변환됨 = {"name":"spring!!"}
    }

    static class Hello {
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }



}
