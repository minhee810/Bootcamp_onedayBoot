package com.springboot.thymeleaf.controller;

import com.springboot.thymeleaf.dto.Address;
import com.springboot.thymeleaf.dto.Card;
import com.springboot.thymeleaf.dto.Member;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@Controller
public class HomeController {
/*

    // http://localhost:8090
    @GetMapping("/")
    public String home(Locale locale, Model model) {
        LocalDateTime now = LocalDateTime.now();

        // 표현 방식을 바꿔주는 포매터  , static ofPattern()임
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 (E) a h시 m분 s초");
        // 서버 접속시간을 거냄 (실제 적용 코드, 지정한 형태로 변경해서 문자열화)
        String formattedNow = now.format(formatter);

        model.addAttribute("serverTime", formattedNow); // model.addAttribute(html에서 찾을 이름 , 전달할 값)
        model.addAttribute("msg", "안녕");

        return "home";
        // home.html 없으면 404
    }

    @GetMapping("/home0101")
    public String home0101(Model model) {
        model.addAttribute("msg", "<b>Hello world!</b>");   // 태그를 전달받았을때 text 속성을 전달받으면 문자 그대로 출력
        return "home0101";
    }

    // http://localhost:8090/home0201
    // 메시지의 키 값을 사용하여 메시지 본문을 가져온다.
    // messages.properties
    @GetMapping("/home0201")
    public String home0201() {
        return "home0201";
    }
    // 객체를 생성한 뒤, 객체에 각 값을 설정해줌
    // model객체에 값을 넣은 뒤 뷰로 전송
    // http://localhost:8090/home0202
    @GetMapping("/home0202")
    public String home0202(Model model) {
        Member member = new Member();
        member.setUserId("hongkd");
        member.setPassword("1234");
        member.setEmail("bbb@ccc.com");
        member.setUserName("홍길동");

        LocalDate dateOfBirth = LocalDate.of(1988, 10, 7);
        member.setDateOfBirth(dateOfBirth);

        model.addAttribute(member);
        return "home0202";
    }

    // http://localhost:8090/home0203
    // member.address.postCode해서 객체에서 값을 꺼내 사용하는 방법 (객체 안에 객체가 갖고 있는 변수 출력방법)
    @GetMapping("/home0203")
    public String home0203(Model model) {
        Member member = new Member();

        Address address = new Address();
        address.setPostCode("080908");
        address.setLocation("seoul");

        member.setAddress(address);
        model.addAttribute(member); // 객체 통째로 던짐

        return "home0203";
    }

    // http://localhost:8090/home0301
    // table 태그에 object속성 값을 설정하고
    // 내부 포함된 태그에 선택 표현식을 사용
    @GetMapping("/home0301")
    public String home0301(Model model) {
        Member member = new Member();
        member.setUserId("hongkd");
        member.setPassword("1234");
        member.setEmail("bbb@ccc.com");
        member.setUserName("홍길동");

        LocalDate dateOfBirth = LocalDate.of(1988, 10, 7);

        member.setDateOfBirth(dateOfBirth);

        model.addAttribute(member);
        return "home0301";
    }

    // http://localhost:8090/home0401
    @GetMapping("/home0401")
    public String home0401() {
        return "home0401";
    }

    @GetMapping("/sub/home0402")
    public String home0402() {
        System.out.println("메렁");
        return "/sub/home0402";
    }

    // 자바 빈즈 프로퍼티 값이 null 인지 체크
    // http://localhost:8090/home0101
    @GetMapping("/home0101")
    public String home0101(Model model) {
        Member member = new Member();

        model.addAttribute(member);
        return "home0101";
    }

    @GetMapping("/home0102")
    public String home0102(Model model) {
        Member member = new Member();
        member.setForeigner(false);

        model.addAttribute(member);

        return "home0102";
    }

    @GetMapping("/home0201")
    public String home0201(Model model) {
        Member member = new Member();
        member.setGender("f");
        model.addAttribute(member);

        return "home0201";
    }

    @GetMapping("/home0301")
    public String home0301(Model model) {
        Member member = new Member();
        String[] hobbyArray = {"Music", "Movie"};

        member.setHobbyArray(hobbyArray);
        model.addAttribute(member);
        return "home0301";
    }
    // 배열과 arrayList 비슷
*/

    @GetMapping("/home0303")
    public String home0303(Model model) {
        Member member = new Member();
        List<Card> cardList = new ArrayList<Card>();

        // -------------------------------------------------------
        Card card1 = new Card();
        card1.setNo("14");

        YearMonth validMonth1 = YearMonth.of(2020, 9);
        card1.setValidMonth(validMonth1);

        cardList.add(card1);
        // -------------------------------------------------------
        Card card2 = new Card();
        card2.setNo("8888");

        YearMonth validMonth2 = YearMonth.of(2020, 9);
        card1.setValidMonth(validMonth2);

        cardList.add(card2);

        // -------------------------------------------------------
        member.setCardList(cardList);

        model.addAttribute(member);

        return "home0303";

    }

    // 지역변수를 선언한 후, 자바 빈즈 프로처티 값을 그 지역 변수에 대입하여 사용

    @GetMapping("/home0401")
    public String home0402(Model model) {
        Member member = new Member();
        member.setUserId("heng");
        model.addAttribute(member);
        return "home0401";
    }


    // 스프링 MVC모델에 저장된 값을 인라인 표기법을 사용하여 표기
    @GetMapping("/home0101")
    public String home010(Model model) {
        model.addAttribute("username", "minhee");
        return "home0101";

/*

        // 비활성화가 된 상태에서는 인라인 표기법 형태인 [[...]]로 그대로 표시된다.
        @GetMapping("/home0201")
        public String home0201 (Model model){
            model.addAttribute("username", "<b>minhee</b>");
            return "home0201";
        }

        @GetMapping("/home0301")
        public String home0301 (Model model){
            model.addAttribute("username", "minhee");

            return "home0301";

        }

        @GetMapping("/home0101")
        public String home0101 (Model model){
            model.addAttribute("msg", "ayo");
            return "home0101";
        }

 */
    }

}
