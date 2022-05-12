package com.travelwithme.withtravel.Service;

import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Account.Form.SignUpForm;
import com.travelwithme.withtravel.Account.UserAccount;

import com.travelwithme.withtravel.Repository.AccountRepository;
import com.travelwithme.withtravel.Tag.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.validation.Valid;

import java.util.List;

@Slf4j
@Service
@Transactional//전체적으로 트랜젝션을 적용하되 읽기전용 트랜젝션은 별도로 적용한다.
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final JavaMailSender javaMailSender;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public Account processNewAccount(SignUpForm signUpForm) {
        Account newAccount = saveNewAccount(signUpForm);
        //newAccount.generateEmailCheckToken();
        sendSignUpConfirmEmail(newAccount);
        return newAccount;
    }
    private Account saveNewAccount(@Valid SignUpForm signUpForm) {

        Account account = Account.builder()
                .email(signUpForm.getEmail())
                .nickname(signUpForm.getNickname())
                .password(passwordEncoder.encode(signUpForm.getPassword()))
                .travelCreatedByWeb(true)
                .travelEnrollmentResultByWeb(true)
                .travelUpdatedByWeb(true)
                .build();
        account.generateEmailCheckToken();
        return accountRepository.save(account);
    }

    public void sendSignUpConfirmEmail(Account newAccount) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(newAccount.getEmail());
        mailMessage.setSubject("스터디올래, 회원 가입 인증");
        mailMessage.setText("/check-email-token?token=" + newAccount.getEmailCheckToken() +
                "&email=" + newAccount.getEmail());
        javaMailSender.send(mailMessage);
    }


    public void login(Account account) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                new UserAccount(account),
                account.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER")));
        //Authentication authentication = authenticationManager.authenticate(token);
        //authentication Manager에 대해서 추가로 공부하고 내용울 추가할 것 이 부분은 정석적이지 않은 방법이다.
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(token);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String emailOrNickname)throws UsernameNotFoundException{
        Account account = accountRepository.findByEmail(emailOrNickname);
        if(account ==null ){
            account = accountRepository.findByNickname(emailOrNickname);
            if(account ==null )
            {            throw new UsernameNotFoundException(emailOrNickname);
            }
        }
        return new UserAccount(account);
    }

    public void completeSignUp(Account account){
        account.completeSignUp();
        login(account);
    }

    public void sendLoginLink(Account account) {
        account.generateLoginEmailToken();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(account.getEmail());
        mailMessage.setSubject("로그인용 이메일 링크 ");
        mailMessage.setText("/login-by-email?token=" + account.getLoginCheckToken() +
                "&email=" + account.getEmail());
        javaMailSender.send(mailMessage);
    }

    public void addTag(Account account, Tag tag) {
        account = accountRepository.findByNickname(account.getNickname());
        account.getTags().add(tag);
        accountRepository.save(account);
    }
}
