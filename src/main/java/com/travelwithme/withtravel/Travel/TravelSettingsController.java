package com.travelwithme.withtravel.Travel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Account.CurrentAccount;
import com.travelwithme.withtravel.Tag.Tag;
import com.travelwithme.withtravel.Tag.TagForm;
import com.travelwithme.withtravel.Tag.TagService;
import com.travelwithme.withtravel.Travel.Form.TravelSettingDescription;
import com.travelwithme.withtravel.Travel.Form.TravelSettingOpenClosed;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/travel/{path}/settings")
@RequiredArgsConstructor
public class TravelSettingsController {

    private final TravelRepository travelRepository;
    private final ModelMapper modelMapper;
    private final TravelSettingService travelSettingService;
    private final ObjectMapper objectMapper;
    private final TagService tagService;

    private String travelSettingDesc = "travel/settings/TravelSettingDescription";
    private String travelSettingOpCl = "travel/settings/TravelSettingOpenClosed";
    private String travelSettingMember = "travel/settings/TravelSettingMember";
    private String travelSettingTag = "travel/settings/TravelSettingTag";

    @GetMapping("/description")
    public String viewTravelSetting(@CurrentAccount Account account, @PathVariable String path, Model model){
        Travel travel = travelRepository.findByPath(path);
        model.addAttribute(account);
        model.addAttribute(travel);
        model.addAttribute("travelDescription", new TravelSettingDescription(travel));
        return travelSettingDesc;
    }

    @PostMapping("/description")
    public String viewTravelSubmit(@CurrentAccount Account account, @PathVariable String path, Model model, Errors errors,
                                   @Valid TravelSettingDescription travelSettingDescription,RedirectAttributes attributes ){
        if (errors.hasErrors()) {
            attributes.addFlashAttribute("error", "수정안에 문제가 있습니다.");
            return "redirect:/travel/"+path+"/settings/description";
        }
        Travel travel = travelRepository.findByPath(path);
        if(travel.getManagers().contains(account))travelSettingService.modifyDescription(travel, travelSettingDescription);
        else{
            attributes.addFlashAttribute("error", "수정 권한이 없습니다.");
        }
        return "redirect:/travel/"+path+"/settings/description";
    }

    @GetMapping("/open-closed")
    public String viewTravelPublish(@CurrentAccount Account account, @PathVariable String path, Model model){
        Travel travel = travelRepository.findByPath(path);
        model.addAttribute(account);
        model.addAttribute(travel);
        model.addAttribute("travelOpenClose",new TravelSettingOpenClosed(travel));
        return travelSettingOpCl;
    }

    @PostMapping("/open-closed")
    public String viewTravelPublishModify(@CurrentAccount Account account, @PathVariable String path, Model model,
                                          @Valid TravelSettingOpenClosed travelSettingOpenClosed ,RedirectAttributes attributes){
        Travel travel = travelRepository.findByPath(path);
        if(travel.getManagers().contains(account))travelSettingService.modifyOpenClosed(travel, travelSettingOpenClosed);
        else{
            attributes.addFlashAttribute("error", "수정 권한이 없습니다.");
        }
        return "redirect:/travel/"+path+"/settings/open-closed";
    }

    @GetMapping("/member")
    public String modifyMember(@CurrentAccount Account account, @PathVariable String path, Model model){
        Travel travel = travelRepository.findByPath(path);
        model.addAttribute(account);
        model.addAttribute(travel);
        return travelSettingMember;
    }

    @GetMapping("/member/{memberName}/remove")
    public String modifyMemberSubmit(@CurrentAccount Account account,@PathVariable String path, @PathVariable String memberName){
        Travel travel = travelRepository.findByPath(path);
        if(travel.getManagers().contains(account))travelSettingService.removeMember(path, memberName);
        return "redirect:/travel/"+path+"/settings/member";
    }

    @GetMapping("/tag")
    public String travelTag(@CurrentAccount Account account, @PathVariable String path, Model model) throws JsonProcessingException {
        Travel travel = travelRepository.findByPath(path);
        model.addAttribute(account);
        Set<Tag> tags = travelSettingService.getTags(travel);
        model.addAttribute("tags", tags.stream().map(Tag::getTagTitle).collect(Collectors.toList()));
        List<String> allTag = tagService.getWhiteList();
        model.addAttribute("whitelist", objectMapper.writeValueAsString(allTag));
        return travelSettingTag;
    }

    @PostMapping("/tag/add")
    public String addTravelTag(@CurrentAccount Account account, @PathVariable String path, Model model, @RequestBody TagForm tagForm){
        Travel travel = travelRepository.findByPath(path);


        return travelSettingTag;
    }

    @PostMapping("tag/remove")
    public String removeTravelTag(@CurrentAccount Account account, @PathVariable String path, Model model, @RequestBody TagForm tagForm){
        return travelSettingTag;
    }



}
