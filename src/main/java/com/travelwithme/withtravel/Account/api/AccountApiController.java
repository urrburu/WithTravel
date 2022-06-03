package com.travelwithme.withtravel.Account.api;

import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Account.AccountService;
import com.travelwithme.withtravel.Account.Form.SignUpForm;
import com.travelwithme.withtravel.Repository.AccountRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
}
