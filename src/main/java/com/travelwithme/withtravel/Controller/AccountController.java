package com.travelwithme.withtravel.Controller;

import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Account.Form.SignUpForm;
import com.travelwithme.withtravel.Account.Validator.SignUpFormValidator;
import com.travelwithme.withtravel.Repository.AccountRepository;
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

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class AccountController {


        private final SignUpFormValidator signUpFormValidator;
        private final JavaMailSender javaMailSender;
        private final AccountRepository accountRepository;

        @InitBinder("signUpForm")
        public void initBinder(WebDataBinder webDataBinder) {
            webDataBinder.addValidators(signUpFormValidator);
        }

        @GetMapping("/sign-up")
        public String signUpForm(Model model) {
            model.addAttribute(new SignUpForm());
            return "account/sign-up";
        }

        @PostMapping("/sign-up")
        public String signUpSubmit(@Valid SignUpForm signUpForm, Errors errors) {
            if (errors.hasErrors()) {
                return "account/sign-up";
            }

            Account account = Account.builder()
                    .email(signUpForm.getEmail())
                    .nickname(signUpForm.getNickname())
                    .password(signUpForm.getPassword()) // TODO encoding 해야함
                    .travelCreatedByWeb(true)
                    .travelEnrollmentResultByWeb(true)
                    .travelUpdatedByWeb(true)
                    .build();
            Account newAccount = accountRepository.save(account);
            newAccount.generateEmailCheckToken();
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(newAccount.getEmail());
            mailMessage.setSubject("스터디올래, 회원 가입 인증");
            mailMessage.setText("/check-email-token?token=" + newAccount.getEmailCheckToken() +
                    "&email=" + newAccount.getEmail());
            javaMailSender.send(mailMessage);

            return "redirect:/";
        }
}
