package com.travelwithme.withtravel.Settings;

import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Account.CurrentAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class SettingsController {

    private final SettingService settingService;

    @GetMapping("/settings/profile")
    public String modifyProfile(@CurrentAccount Account account, Model model){
        model.addAttribute(account);
        model.addAttribute(new Profile(account));
        return "Profile/modifyProfile";
    }

    @PostMapping("/settings/profile")
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
            return "redirect:"+"/profile/"+account.getNickname();
        }
        if(!password.getPassword().equals(password.getPasswordCheck())){
            attributes.addFlashAttribute("error", "비밀번호가 서로 다릅니다 비밀번호를 확인해주세요.");
            return "redirect:/settings/password";
        }
        settingService.modifyPassword(account, password);
        attributes.addFlashAttribute("message", "비밀번호가 바뀌었습니다.");
        return "redirect:/settings/password";
    }
}
