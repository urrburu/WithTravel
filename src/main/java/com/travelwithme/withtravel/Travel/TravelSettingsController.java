package com.travelwithme.withtravel.Travel;

import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Account.CurrentAccount;
import com.travelwithme.withtravel.Travel.Form.TravelSettingDescription;
import com.travelwithme.withtravel.Travel.Form.TravelSettingOpenClosed;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/travel/{path}/settings")
@RequiredArgsConstructor
public class TravelSettingsController {

    private final TravelRepository travelRepository;
    private final ModelMapper modelMapper;
    private final TravelSettingService travelSettingService;

    private String travelSettingDesc = "/travel/settings/TravelSettingDescription";
    private String travelSettingOpCl = "/travel/settings/TravelSettingOpenClosed";

    @GetMapping("/description")
    public String viewTravelSetting(@CurrentAccount Account account, @PathVariable String path, Model model){

        return travelSettingDesc;
    }

    @PostMapping("/description")
    public String viewTravelSubmit(@CurrentAccount Account account, @PathVariable String path, Model model, @Valid TravelSettingDescription travelSettingDescription){

        return "redirect:/travel/"+path+"/settings/description";
    }

    @GetMapping("/open-closed")
    public String viewTravelPublish(@CurrentAccount Account account, @PathVariable String path, Model model){

        return travelSettingOpCl;
    }

    @PostMapping("/open-closed")
    public String viewTravelPublishModify(@CurrentAccount Account account, @PathVariable String path, Model model, @Valid TravelSettingOpenClosed travelSettingOpenClosed){

        return "redirect:/travel/"+path+"/settings/open-closed";
    }





}
