package com.travelwithme.withtravel.Settings;

import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Account.CurrentAccount;
import com.travelwithme.withtravel.Settings.Form.NicknameForm;
import com.travelwithme.withtravel.Settings.Form.Notification;
import com.travelwithme.withtravel.Settings.Form.Password;
import com.travelwithme.withtravel.Settings.Form.Profile;
import com.travelwithme.withtravel.Settings.Validator.NicknameValidator;
import com.travelwithme.withtravel.Settings.Validator.PasswordFormValidator;
import com.travelwithme.withtravel.Tag.Tag;
import com.travelwithme.withtravel.Tag.TagForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class SettingsController {

    private final SettingService settingService;
    private static final String SETTING_PROFILE_URL = "/settings/profile";
    private static final String SETTING_NOTIFICATIONS_URL = "/settings/notification";
    private static final String SETTING_NOTIFICATIONS_Location = "Profile/modifyNotification";
    private static final String SETTING_TAGS_URL = "/settings/tags";
    private static final String SETTING_TAGS_Location = "Profile/tags";
    private static final String SETTING_NICKNAME_URL = "/settings/account";
    private static final String SETTING_NICKNAME_LOCATION = "Profile/modifyNickname";


    private final NicknameValidator nicknameValidator;

    @InitBinder("password")
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(new PasswordFormValidator());
    }

    @InitBinder("nicknameForm")
    public void nicknameFormInitBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(nicknameValidator);
    }

    @GetMapping(SETTING_PROFILE_URL)
    public String modifyProfile(@CurrentAccount Account account, Model model){
        model.addAttribute(account);
        model.addAttribute(new Profile(account));
        return "Profile/modifyProfile";
    }

    @PostMapping(SETTING_PROFILE_URL)
    public String submitProfile(@CurrentAccount Account account, @Valid Profile profile, Errors errors, Model model
    , RedirectAttributes redirectAttributes){
        if(errors.hasErrors()){
            model.addAttribute(account);
            return "Profile/modifyProfile";
        }
        settingService.modifyProfile(account, profile);
        redirectAttributes.addFlashAttribute("message","프로필 수정이 완료되었습니다.");
        return "redirect:"+"/profile/"+account.getNickname();
    }

    @GetMapping("/settings/password")
    public String modifyPassword(@CurrentAccount Account account, Model model){
        model.addAttribute(account);
        model.addAttribute(new Password());
        return "Profile/modifyPassword";
    }

    @PostMapping("/settings/password")
    public String submitPassword(@CurrentAccount Account account, Model model, @Valid Password password, Errors errors, RedirectAttributes attributes){
        if(errors.hasErrors()){
            model.addAttribute(account);
            return "Profile/modifyPassword";
        }
        settingService.modifyPassword(account, password);
        attributes.addFlashAttribute("message", "비밀번호가 바뀌었습니다.");
        return "redirect:/settings/password";
    }

    @GetMapping(SETTING_NOTIFICATIONS_URL)
    public String notificationSetting (@CurrentAccount Account account, Model model){
        model.addAttribute(account);
        model.addAttribute(new Notification(account));

        return SETTING_NOTIFICATIONS_Location;
    }
    @PostMapping(SETTING_NOTIFICATIONS_URL)
    public String notificationSummit(@CurrentAccount Account account,Model model,
                                     @Valid Notification notification, Errors errors, RedirectAttributes attributes){
        if(errors.hasErrors()){
            model.addAttribute(account);
            model.addAttribute(new Notification());
            return SETTING_NOTIFICATIONS_Location;
        }
        settingService.modifyNotification(account, notification);
        attributes.addFlashAttribute("message", "알림설정이 변경되었습니다.");
        return "redirect:/settings/notification";
    }

    @GetMapping(SETTING_NICKNAME_URL)
    public String NicknameSetting(@CurrentAccount Account account, Model model){
        model.addAttribute(account);
        model.addAttribute(new NicknameForm(account));
        return SETTING_NICKNAME_LOCATION;
    }

    @PostMapping(SETTING_NICKNAME_URL)
    public String NicknameSummit(@CurrentAccount Account account, @Valid NicknameForm nicknameForm,
                                 Model model, Errors errors, RedirectAttributes attributes){
        if(errors.hasErrors()){
            model.addAttribute(account);
            model.addAttribute(new NicknameForm(account));
            model.addAttribute(errors);
            return SETTING_NICKNAME_LOCATION;
        }
        settingService.modifyNickname(account, nicknameForm);
        attributes.addFlashAttribute("message", "닉네임이 성공적으로 변경되었습니다.");
        //로그아웃 보다 로그인 처리를 다시 해주면 되는 일이었다.

        return "redirect:"+SETTING_NICKNAME_URL;
    }

    @GetMapping(SETTING_TAGS_URL)
    public String tagSetting(@CurrentAccount Account account, Model model){
        model.addAttribute(account);
        Set<Tag> tags = settingService.getTags(account);
        model.addAttribute("tags", tags.stream().map(Tag::getTagTitle).collect(Collectors.toList()));
        return SETTING_TAGS_Location;
    }

    @PostMapping("/settings/tags/add")
    public String tagSubmit(@CurrentAccount Account account, @RequestBody TagForm tagForm, Model model){
        String title = tagForm.getTagTitle();
        settingService.appendTag(account, title);


        return "redirect:/"+SETTING_TAGS_URL;
    }


}
