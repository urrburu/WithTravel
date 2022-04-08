package with.travel.withTravel.Member;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import with.travel.withTravel.Domain.Account;
import with.travel.withTravel.Repository.MemberRepository;

import javax.validation.Valid;


@Controller
@RequiredArgsConstructor
public class MemberController {

    private final SignUpFormValidator signUpFormValidator;
    private final MemberRepository memberRepository;
    private final JavaMailSender javaMailSender;

    @InitBinder("signUpForm")
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(signUpFormValidator);
    }
    @GetMapping("/sign-up")
    public String AccountSignup(Model model){
        model.addAttribute(new SignUpForm());
        return "member/sign-up";
    }

    @PostMapping("/sign-up")
    public String signUpSubmit(@Valid SignUpForm signUpForm, Errors errors){
        if(errors.hasErrors()) {
            return "member/sign-up";
        }
        Account account = Account.builder()
                .email(signUpForm.getEmail())
                .nickname(signUpForm.getNickname())
                .password(signUpForm.getPassword())//TODO SHA-256인코딩 반드시 해야함!!!!!
                .emailVerified(false)
                .build();
        Account newAccount = memberRepository.save(account);
        newAccount.generateEmailCheckToken();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(newAccount.getEmail());
        mailMessage.setSubject("위드 트래블, 회원 가입 인증");
        mailMessage.setText("/check-email-token?token="+ newAccount.getEmailCheckToken()+
                "&email="+ newAccount.getEmail());
        javaMailSender.send(mailMessage);
        return "redirect:/";
    }

    @GetMapping("/account")
    public String AccountView(Model model){
        return "member/memberView";
    }
}
