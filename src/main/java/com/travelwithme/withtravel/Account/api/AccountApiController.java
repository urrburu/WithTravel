package com.travelwithme.withtravel.Account.api;

import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Account.AccountService;
import com.travelwithme.withtravel.Account.Form.SignUpForm;
import com.travelwithme.withtravel.Account.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AccountApiController {
    private final AccountService accountService;
    private final AccountRepository accountRepository;

    @PostMapping("/api/v1/account/new")
    public CreateAccountResponse saveMemberV1(@RequestBody @Valid SignUpForm signUpForm){
       Long id =  accountService.processNewAccount(signUpForm).getId();
       return new CreateAccountResponse(id);
    }

    @Data
    static class CreateAccountResponse{
        private Long id;

        public CreateAccountResponse(Long id){
            this.id = id;
          }
    }
    @GetMapping("/api/v1/account/all")
    public List<Account> accountList(){
        return accountService.findAccounts();
    }

    @GetMapping("/api/v2/account/all")
    public Result accountListV2(){
        List<Account> accounts = accountService.findAccounts();
        List<MemberDto> accountList = accounts.stream()
                .map(m->new MemberDto(m.getNickname()))
                .collect(Collectors.toList());
        return new Result(accountList);
    }

    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }
    @Data
    @AllArgsConstructor
    static class MemberDto{
        private String name;
    }
}
