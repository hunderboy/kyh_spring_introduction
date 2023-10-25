package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class HomeController {
    // Controller 가 우선이다.
    // home 화면 작성시, 아래 처럼 getMapping("/") 하여, 먼저 컨트롤러 단에서 우선순위가 설정되게 끔 한다.
    @GetMapping("/") // localhost:8080 호출 시 index.html 이 불러지는 게 아닌 home.html 을 호출 하게 된다.
    public String home() {
        return "home";
    }


    // get 방식 + 파라미터
    @GetMapping("market-list")
    public String helloMvc(
//            @RequestParam("category_list") String marketList,
            @RequestParam("category_list") List<String> marketList,
            Model model
    ) {
        model.addAttribute("list", marketList);
        return "markets/market-list";
    }

    @GetMapping("/a-1")
    public String a1(@RequestParam("data") List<String> param, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("data", param);

        return "redirect:/b-1";
    }

    @GetMapping("/b-1")
    public String b1(@RequestParam("data") List<String> data, Model model) {
        // 여기에서 param을 활용하여 화면을 구성
        model.addAttribute("data", data); // 모델에 파라미터 추가
        return "markets/b-1-view"; // 화면 이름 반환
    }


}