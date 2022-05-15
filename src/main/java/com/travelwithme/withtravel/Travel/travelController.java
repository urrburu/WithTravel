package com.travelwithme.withtravel.Travel;

import com.travelwithme.withtravel.Account.Account;
import com.travelwithme.withtravel.Account.CurrentAccount;

import com.travelwithme.withtravel.Travel.Form.travelForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class travelController {

    private final static String travelUrl = "/travel/";
    private String travelLocation = "/travel/allTravel";


    @GetMapping(travelUrl)
    public String travelView(@CurrentAccount Account account, Model model){
        if(account==null){


        }else{


        }
        return travelLocation;
    }

    @PostMapping(travelUrl)
    public String travelSubmit(@CurrentAccount Account account, Model model, @Valid travelForm travelform){



        return "redirect:/"+travelUrl;
    }

}
