package com.travelwithme.withtravel.Settings;

import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Account.CurrentAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String submitProfile(@CurrentAccount Account account, @Valid Profile profile, Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute(account);
            return "Profile/modifyProfile";
        }
        settingService.modifyProfile(account, profile);
        return "redirect:/settings/profile";
    }

    @GetMapping("/settings/password")
    public String modifyPassword(@CurrentAccount Account account, Model model){
        model.addAttribute(account);
        model.addAttribute(new Password());
        return "Profile/modifyPassword";
    }
}
