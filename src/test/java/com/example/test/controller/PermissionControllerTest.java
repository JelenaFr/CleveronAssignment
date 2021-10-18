package com.example.test.controller;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PermissionControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    WebApplicationContext wac;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
    }

    @Test
    void createNewFirstLevelPermission() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/new1/");
        ResultActions result = mockMvc.perform(request);
        result.andExpect(status().isOk());
    }


    @Test
    void findAllSecondLevelsByFirstLevelParam() throws Exception {
        this.mockMvc.perform(get("/level2/{param}/", 1)).andExpect(status().isOk());


    }

    @Test
    void findAllThirdLevelsBySecondLevelParam() throws Exception {
        this.mockMvc.perform(get("/level2/5/"))
            .andExpect(status().isOk());
    }


}
