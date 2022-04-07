package with.travel.withTravel.Member;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    @GetMapping("/sign-up")
    public String AccountSignup(Model model){
        model.addAttribute("signUpForm", new SignUpForm());
        return "member/sign-up";
    }

    @GetMapping("/account")
    public String AccountView(Model model){
        return "member/memberView";
    }
}
