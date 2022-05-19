package com.travelwithme.withtravel.Travel;

import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Account.CurrentAccount;
import com.travelwithme.withtravel.Travel.Form.TravelForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TravelController {

    private final static String travelUrl = "/travel/";
    private String travelLocation = "/travel/allTravel";
    private final static String travelMakeUrl = "/travel/make";
    private String travelMakeLocation = "/travel/makeTravel";
    private final static String spotUrl = "/travel/spot";
    //Todo add 콜과 remove 콜을 별도로 만들어줄 예정
    private String spotLocation = "/travel/modifySpot";

    private static TravelService travelService;


    @GetMapping(travelUrl)
    public String travelView(@CurrentAccount Account account, Model model){
        //모든 여행들을 보여주는 뷰화면
        if(account==null){
        //Todo 만약 account가 없을 경우, 지금 마감에 가까운 9개의 여행을 보여줄 것
            List<Travel> travels = travelService.findNineTravel(LocalDateTime.now());
            model.addAttribute(account);
            model.addAttribute(travels);
        }else{
        //Todo 만약 account가 있을 경우, 등록되었지만 아직 가지 않은 여행, 사람을 구하는 여행, 다녀올 여행 순서로 보여줄 예정.

        }
        return travelLocation;
    }


    @GetMapping(travelMakeUrl)
    public String travelMakeView(@CurrentAccount Account account, Model model){
        //새로운 여행을 만드는 뷰화면을 보여주는 Get call
        model.addAttribute(account);
        model.addAttribute(new TravelForm());
        return travelMakeLocation;
    }

    @PostMapping(travelMakeUrl)
    public String travelMakeSubmit(@CurrentAccount Account account, Model model, @Valid TravelForm travelForm, RedirectAttributes attributes){
        /*
        if(account.isEmailVerified()==false){
            //이메일이 확인되지 않은 이용자에게는 서비스를 제공할 수 없음을 명시
            model.addAttribute(account);
            model.addAttribute(new TravelForm());
            attributes.addFlashAttribute("message", "이메일 인증이 되지 않은 유저에게는 위드 트래블의 서비스를 제공할 수 없습니다.");
            return "redirect:/"+travelMakeUrl;
        }
         */
        travelService.newTravelMake(travelForm, account);
        return "redirect:/"+travelUrl;
    }




}
