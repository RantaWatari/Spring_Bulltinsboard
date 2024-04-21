package com.tuyano.springboot;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.tuyano.springboot.repositories.AccountDataRepository;

import jakarta.transaction.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@WebAppConfiguration
public class bulletinBoardControllerTest {

	@Autowired
	AccountDataRepository accountRepository;
	Certification certification;
	TimeGenerate timeGenerate;
	
    @Autowired
    private MockMvc mockMvc;
    
    @DisplayName("testIndexGet")
    @Test
    public void testIndexGet() throws Exception {
        
        mockMvc.perform(get("/"))
            .andExpect(status().is(302))
            .andExpect(redirectedUrl("/login"))
            .andDo(print()); 
    }

    
    @DisplayName("testLoginGet")
    @Test
    public void testLoginGet() throws Exception {
        mockMvc.perform(get("/login")) 
            .andExpect(status().is(200)) 
            .andExpect(view().name("login"))
            .andDo(print());
    }
    

	@DisplayName("testLoginFormSuccess")
    @Test
    @Transactional
    public void testLoginFormSuccess() throws Exception {
    	String name = "testuser";
    	String password = "testpass";
        mockMvc.perform(post("/login")
                .param("name", name)
                .param("password", password))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("/home/"+name))
                .andDo(print());
        ResultActions a = mockMvc.perform(null);
        a.andExpect(null);
        
    }

    
    @DisplayName("testLoginFormFailure")
    @Test
    @Transactional
    public void testLoginFormFailure() throws Exception {
    	String name = "";
    	String password = "";
    	mockMvc.perform(post("/login")
                .param("name", name)
                .param("password", password))
                .andExpect(status().is(200))
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("msg"))
                .andExpect(model().attribute("msg", "Not Acccount!!"))
                .andDo(print());
    }

    @DisplayName("testLogoutFormSucces")
    @Test
    @Transactional
    public void testLogoutFormSucces() throws Exception {
    	String name = "testuser";
    	mockMvc.perform(post("/logout")
                .param("name", name))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl("/login"))
                .andDo(print());
    }

   
    private List<String[]> testCaseList(){
        String[] testIndexGet = {"/","302","/login"};
        String[] testLoginGet= {"/","302","/login"};
        String[] testLoginFormSuccess = {"/","302","/login"};
        String[] testLoginFormFailure = {"/","302","/login"};

        return null;
    }

    // 各テストメソッドのmockMvcを共通メソッドとして引数を貰ったほうが楽かも。

}
