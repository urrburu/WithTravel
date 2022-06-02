package com.travelwithme.withtravel.Travel;

import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Account.CurrentAccount;
import com.travelwithme.withtravel.Repository.TravelRepository;
import com.travelwithme.withtravel.Travel.Form.TravelContents;
import com.travelwithme.withtravel.Travel.Form.TravelRecruiting;
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

    @GetMapping("/description")
    public String viewTravelSetting(@CurrentAccount Account account, @PathVariable String path, Model model){
        Travel travel = travelRepository.findByTravelName(path);
        model.addAttribute(account);
        model.addAttribute(travel);
        model.addAttribute("travelPublishForm", new TravelRecruiting(travel));
        return "/travel/settings/TravelContents";
    }

    @PostMapping("/description")
    public String viewTravelSubmit(@CurrentAccount Account account, @PathVariable String path, @Valid TravelContents travelContents, Model model){
        Travel travel = travelRepository.findByTravelName(path);
        if(travel.getManagers().contains(account)){
            travelSettingService.modifyTravel(travel, travelContents);
        }
        return "redirect:/travel/"+travel.getTravelName();
    }

    @GetMapping("/open-closed")
    public String viewTravelPublish(@CurrentAccount Account account, @PathVariable String path, Model model){
        Travel travel = travelRepository.findByTravelName(path);
        model.addAttribute(account);
        model.addAttribute(travel);
        model.addAttribute("travelForm", new TravelRecruiting(travel));
        return "/travel/settings/TravelContents";
    }

    @PostMapping("/open-closed")
    public String viewTravelPublishModify(@CurrentAccount Account account, @PathVariable String path, @Valid TravelRecruiting travelRecruiting, Model model){
        Travel travel = travelRepository.findByTravelName(path);
        if(travel.getManagers().contains(account)){
            travelSettingService.modifyTravelPublish(travel, travelRecruiting);
        }
        return "redirect:/travel/"+travel.getTravelName();
    }

    @GetMapping("/tag")
    public String viewTravelModifyTag(@CurrentAccount Account account, @PathVariable String path, Model model){
        Travel travel = travelRepository.findByTravelName(path);
        model.addAttribute(account);
        model.addAttribute(travel);
        model.addAttribute("travelForm", new TravelContents(travel));
        return "/travel/settings/TravelContents";
    }

    @PostMapping("/tag")
    public String viewTravelModifyTagSubmit(@CurrentAccount Account account, @PathVariable String path, @Valid TravelContents travelContents, Model model){
        Travel travel = travelRepository.findByTravelName(path);
        if(travel.getManagers().contains(account)){
            travelSettingService.modifyTravel(travel, travelContents);
        }
        return "redirect:/travel/"+travel.getTravelName();
    }

    @GetMapping("/member")
    public String viewTravelModifyMember(@CurrentAccount Account account, @PathVariable String path, Model model){
        Travel travel = travelRepository.findByTravelName(path);
        model.addAttribute(account);
        model.addAttribute(travel);
        model.addAttribute("travelForm", new TravelContents(travel));
        return "/travel/settings/TravelContents";
    }

    @PostMapping("/member")
    public String viewTravelModifyMemberSubmit(@CurrentAccount Account account, @PathVariable String path, @Valid TravelContents travelContents, Model model){
        Travel travel = travelRepository.findByTravelName(path);
        if(travel.getManagers().contains(account)){
            travelSettingService.modifyTravel(travel, travelContents);
        }
        return "redirect:/travel/"+travel.getTravelName();
    }



}
