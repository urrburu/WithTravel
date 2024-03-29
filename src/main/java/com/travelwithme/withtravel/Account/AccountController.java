package com.travelwithme.withtravel.Account;


import com.travelwithme.withtravel.Account.Form.SignUpForm;
import com.travelwithme.withtravel.Account.Validator.SignUpFormValidator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class AccountController {


        private final SignUpFormValidator signUpFormValidator;
        private final AccountService accountService;
        private final AccountRepository accountRepository;
        private final String emailLoginLocation = "account/emailLogin";
        private final String emailLoginURL = "/email-login";
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

            Account account = accountService.processNewAccount(signUpForm);
            accountService.login(account);
            return "redirect:/";
        }


        @GetMapping("/check-email-token")
        public String checkEmailToken(String token, String email, Model model){

            String checkedEmail = "account/checkedEmail";
            Account account = accountRepository.findByEmail(email);
            if(account == null){
                model.addAttribute("error", "wrong.email");
                return checkedEmail;
            }
            if(!account.CheckValidToken(token)){
                model.addAttribute("error", "wrong.token");
                return checkedEmail;
            }

            accountService.completeSignUp(account);
            model.addAttribute("nickname", account.getNickname());
            return checkedEmail;
        }
        @GetMapping("/check-email")
        public String checkEmail(@CurrentAccount Account account, Model model){
            model.addAttribute(account);

            return "account/ReCheckEmail";
        }
        @GetMapping("/resend-email")
        public String ResendEmail(@CurrentAccount Account account, Model model){
            if(account.canSendConfirmEmail()==false){
                model.addAttribute("error", "이메일 로그인은 1시간 뒤에 다시 사용할 수 있습니다.");
                model.addAttribute(account);
                return "account/ReCheckEmail";
            }
            model.addAttribute(account);
            accountService.sendSignUpConfirmEmail(account);
            return "account/ReCheckEmail";
        }
        @GetMapping("/findPassword")
        public String FindPassword(Model model){
            return "redirect:/";
        }

        @GetMapping("/profile/{nickname}")
        public String viewProfile(@PathVariable String nickname, Model model, @CurrentAccount Account account){
            Account byNickname = accountRepository.findByNickname(nickname);
            if(nickname == null ){
                throw new IllegalArgumentException(nickname+"에 해당하는 사용자가 없습니다.");
            }
            model.addAttribute("account", byNickname);
            model.addAttribute("isOwner", byNickname.equals(account));
            return "Profile/profile";
        }

        @GetMapping(emailLoginURL)
        public String emailLogin(){
            return emailLoginLocation;
        }

        @PostMapping(emailLoginURL)
        public String emailLoginSummit(String email, Model model, RedirectAttributes attributes){
            Account account = accountRepository.findByEmail(email);
            if(account==null){
                model.addAttribute("error", "유효한 이메일 주소가 아닙니다.");
                return emailLoginLocation;
            }
            if(!account.canSendConfirmEmail()){
                model.addAttribute("error", "이메일 로그인은 1시간 뒤에 다시 사용할 수 있습니다.");
                return emailLoginLocation;
            }
            accountService.sendLoginLink(account);
            attributes.addFlashAttribute("message", "이메일 인증메일을 발송 했습니다.");
            return "redirect:/email-login";
        }
        @GetMapping("/login-by-email")
        public String loginByEmail(String token, String email, Model model){
            Account account = accountRepository.findByEmail(email);
            String view = emailLoginLocation;
            if(account==null ||!account.isValidToken(token)){
                model.addAttribute("error", "로그인 할 수 없습니다.");
                return view;
            }
            accountService.login(account);
            return "redirect:/";
        }



}
