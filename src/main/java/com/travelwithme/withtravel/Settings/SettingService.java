package com.travelwithme.withtravel.Settings;

import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Account.Address;
import com.travelwithme.withtravel.Repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SettingService {

    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;

    public void modifyProfile(Account account, Profile profile) {
        account.setBio(profile.getBio());
        account.setUrl(profile.getUrl());
        account.setOccupation(profile.getOccupation());
        account.setAddress(new Address(profile.getCity(), profile.getLocalNameOfCity(), profile.getProvince()));
        account.setProfileImage(profile.getProfileImage());
        accountRepository.save(account);
    }

    public void modifyPassword(Account account, Password password) {
        account.setPassword(passwordEncoder.encode(password.getPassword()));
        accountRepository.save(account);
    }
}
