package com.travelwithme.withtravel.Controller;


import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Account.Form.SignUpForm;
import com.travelwithme.withtravel.Account.Validator.SignUpFormValidator;

import com.travelwithme.withtravel.Repository.AccountRepository;
import com.travelwithme.withtravel.Service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class AccountController {


        private final SignUpFormValidator signUpFormValidator;
        private final AccountService accountService;
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
            accountService.processNewAccount(signUpForm);
            return "redirect:/";
        }

        @GetMapping("/check-email-token")
        public String checkEmailToken(String token, String email, Model model){
            Account account = accountRepository.findByEmail(email);
            String checkedEmail = "account/checkedEmail";
            if(account == null){
                model.addAttribute("error", "wrong.email");
                return checkedEmail;
            }
            if(!account.getEmailCheckToken().equals(token)){
                model.addAttribute("error", "wrong.token");
                return checkedEmail;
            }

            account.setEmailVerified(true);
            account.setJoinedAt(LocalDateTime.now());
            model.addAttribute("nickname", account.getNickname());
            return checkedEmail;
        }
        @GetMapping("/email-login")
        public String loginByEmail(){

            return "account/emailLogin";
        }



}
