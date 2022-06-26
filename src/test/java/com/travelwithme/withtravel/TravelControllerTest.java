package com.travelwithme.withtravel;

import com.travelwithme.withtravel.Plan.PlanRepository;
import com.travelwithme.withtravel.Travel.TravelController;
import com.travelwithme.withtravel.Travel.TravelRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class TravelControllerTest {

    @Autowired    MockMvc mockMvc;
    @Autowired    TravelController travelController;
    @Autowired    TravelRepository travelRepository;
    @Autowired    PlanRepository planRepository;


    @BeforeEach
    public void BeforeTest(){


    }

    @AfterEach
    public void AfterTest(){


    }
}
