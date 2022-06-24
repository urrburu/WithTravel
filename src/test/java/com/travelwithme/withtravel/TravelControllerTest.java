package com.travelwithme.withtravel;

import com.travelwithme.withtravel.Travel.TravelController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class TravelControllerTest {

    @Autowired    MockMvc mockMvc;
    @Autowired    TravelController travelController;
}
