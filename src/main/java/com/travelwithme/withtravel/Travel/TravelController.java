package com.travelwithme.withtravel.Travel;

import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Account.CurrentAccount;
import com.travelwithme.withtravel.Repository.TravelRepository;
import com.travelwithme.withtravel.Spot.SpotForm;
import com.travelwithme.withtravel.Travel.Form.TravelForm;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TravelController {

    private final static String travelUrl = "/travel/";
    private String travelLocation = "/travel/allTravel";
    private final static String travelMakeUrl = "/travel/make";
    private String travelMakeLocation = "/travel/makeTravel";
    //Todo add 콜과 remove 콜을 별도로 만들어줄 예정
    private String spotLocation = "/travel/modifySpot";

    private final TravelService travelService;
    private final TravelRepository travelRepository;
    private final ModelMapper modelMapper;


    @GetMapping(travelUrl)
    public String travelView(@CurrentAccount Account account, Model model){
        /* 404 error
        //모든 여행들을 보여주는 뷰화면
        if(account==null){
        //Todo 만약 account가 없을 경우, 지금 마감에 가까운 9개의 여행을 보여줄 것
            List<Travel> travels = travelService.findNineTravel(LocalDateTime.now());
            model.addAttribute(account);
            model.addAttribute(travels);
        }else{
        //Todo 만약 account가 있을 경우, 등록되었지만 아직 가지 않은 여행, 사람을 구하는 여행, 다녀올 여행 순서로 보여줄 예정.

        }
         */
        List<Travel> travels = travelRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        if(travels.size()!=0){model.addAttribute(travels);}
        model.addAttribute(account);
        return travelLocation;
    }

    @GetMapping("/travel/{travelName}")
    public String viewTravel(@PathVariable String travelName, Model model, @CurrentAccount Account account){
        Travel byTravelName = travelRepository.findByTravelName(travelName);
        if(byTravelName == null ){
            throw new IllegalArgumentException(travelName+"에 해당하는 여행이 없습니다.");
        }
        model.addAttribute("travel", byTravelName);
        return "travel/travelPick";
    }

    @GetMapping(travelMakeUrl)
    public String travelMakeView(@CurrentAccount Account account, Model model){
        //새로운 여행을 만드는 뷰화면을 보여주는 Get call
        model.addAttribute(account);
        model.addAttribute(new TravelForm());
        return travelMakeLocation;
    }

    @PostMapping(travelMakeUrl)
    public String travelMakeSubmit(@CurrentAccount Account account, Model model, @Valid TravelForm travelForm,
                                   RedirectAttributes attributes, Error error){
        /*
        if(account.isEmailVerified()==false){
            //이메일이 확인되지 않은 이용자에게는 서비스를 제공할 수 없음을 명시
            model.addAttribute(account);
            model.addAttribute(new TravelForm());
            attributes.addFlashAttribute("message", "이메일 인증이 되지 않은 유저에게는 위드 트래블의 서비스를 제공할 수 없습니다.");
            return "redirect:/"+travelMakeUrl;
        }
         */
        Travel travel = travelService.newTravelMake(travelForm, account);
        model.addAttribute(account);
        return "redirect:/travel/"+travel.getTravelName();
    }
    /*
    @GetMapping(spotUrl)
    public String Spotmodify(@CurrentAccount Account account, String travelName, Model model){
        //Todo manager가 아니라면 수정할 권한이 없기 때문에 버튼을 만들어 주지 않아야함 + 해당 포스트 입력이 들어왔을때 안된다고 확인
        Travel travel = travelRepository.findByTravelName(travelName);
        model.addAttribute(account);
        model.addAttribute(travel);
        model.addAttribute(new SpotForm());
        return spotLocation;
    }
*/
    @GetMapping("/travel/{travelName}/new-spot")
    public String SpotAddView(@CurrentAccount Account account, @PathVariable String travelName,Model model, RedirectAttributes attributes){
        Travel travel = travelRepository.findByTravelName(travelName);
        if(!travel.getManagers().contains(account)){
            attributes.addFlashAttribute("error", "이 여행을 수정할 권한이 없습니다.");
            return "redirect:/travel/"+travelName;
        }
        model.addAttribute(account);
        model.addAttribute(travel);
        model.addAttribute(new SpotForm());
        return "travel/modifySpot";
    }
    @PostMapping("/travel/{travelName}/new-spot")
    public String SpotAddSubmit(@CurrentAccount Account account, @PathVariable String travelName, @Valid SpotForm spotForm, RedirectAttributes attributes){
        Travel travel = travelRepository.findByTravelName(travelName);
        if(!travel.getManagers().contains(account)){
            attributes.addFlashAttribute("error", "이 여행을 수정할 권한이 없습니다.");
            return "redirect:/travel/"+travelName;
        }
        travelService.addSpot(travel, spotForm);
        return "redirect:/travel/"+travelName;
    }
    @PostMapping("/travel/{travelName}/{spotName}/spot-remove")
    public String SpotRemoveSubmit(@CurrentAccount Account account, @PathVariable String travelName, @PathVariable String spotName, RedirectAttributes attributes){
        Travel travel = travelRepository.findByTravelName(travelName);
        if(!travel.getManagers().contains(account)){
            attributes.addFlashAttribute("error", "이 여행을 수정할 권한이 없습니다.");
            return "redirect:/travel/"+travelName;
        }

        travelService.removeSpot(travel, spotName);
        return "redirect:/travel/"+travelName;
    }

    @GetMapping("/travel/{travelName}/join")
    public String memberJoin(@PathVariable String travelName, Model model, @CurrentAccount Account account){
        Travel travel = travelRepository.findByTravelName(travelName);
        travelService.addMember(travel, account);
        return "redirect:"+travelUrl;

    }

}
