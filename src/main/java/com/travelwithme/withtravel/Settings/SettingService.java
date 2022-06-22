package com.travelwithme.withtravel.Settings;

import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Account.Address;
import com.travelwithme.withtravel.Account.AccountRepository;
import com.travelwithme.withtravel.Tag.TagRepository;
import com.travelwithme.withtravel.Account.AccountService;
import com.travelwithme.withtravel.Settings.Form.NicknameForm;
import com.travelwithme.withtravel.Settings.Form.Notification;
import com.travelwithme.withtravel.Settings.Form.Password;
import com.travelwithme.withtravel.Settings.Form.Profile;
import com.travelwithme.withtravel.Tag.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class SettingService {

    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final AccountService accountService;
    private final ModelMapper modelMapper;
    private final TagRepository tagRepository;

    public void modifyProfile(Account account, Profile profile) {

        Address address = new Address(profile.getCity(), profile.getLocalNameOfCity(), profile.getProvince());
        modelMapper.map(profile, account);
        account.setAddress(address);

        accountRepository.save(account);
    }

    public void modifyPassword(Account account, Password password) {
        account.setPassword(passwordEncoder.encode(password.getPassword()));
        accountRepository.save(account);
    }


    public void modifyNotification(Account account, Notification notification) {
        modelMapper.map(notification, account);
        accountRepository.save(account);
        /*
        * 유의점 : 변경감지가 더 유리한 측면이 있어서 변경감지로 이를 구현했다.
        * 위의 코드를 보면 merge로 구현할 수도 있다. 하지만 set 함수를 막고 변경감지로 이를 구현하는게 더
        * 안전한 설계가 될 것 같아 변경감지로 이를 바꿀 예정이다.
        *
        * 예상치 못한 상황이 발생했다. 엔티티 매니저가 당연히 JPA 리포지토리를 만드는데 포함되어 있을거라고 생각했지만
        * 그렇지 않았고 엔티티 매니저
        * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.entity-persistence
        * */

    }

    public void modifyNickname(Account account, NicknameForm nicknameForm){
        account.setNickname(nicknameForm.getNewNickname());
        accountRepository.save(account);
        accountService.login(account);
    }


    public void appendTag(Account account, String title) {
        Tag tag = tagRepository.findByTagTitle(title);
        if(tag==null){
            tagRepository.save(Tag.builder()
                            .tagTitle(title)
                            .build());
            Tag newTag = tagRepository.findByTagTitle(title);
            accountService.addTag(account, newTag);
        }
        else{
            accountService.addTag(account, tag);
        }
    }

    public Set<Tag> getTags(Account account){
        Set<Tag> tags = accountRepository.findByNickname(account.getNickname()).getTags();
        return tags;
    }

    public void removeTag(Account account, String title) {
        Tag tag = tagRepository.findByTagTitle(title);
        if(tag==null){return;}
        accountService.removeTag(account, tag);


    }


}
