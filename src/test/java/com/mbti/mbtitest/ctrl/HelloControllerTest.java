package com.mbti.mbtitest.ctrl;

import com.mbti.mbtitest.ctrl.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// SpringRunner는 테스트와 제이유닛 사이에 연결자 역할
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {
    @Autowired
    // Spring MVC TEST의 시작점
    // http get, post 등에 대한 api test
    private MockMvc mvc;

    @Test
    public void hello_is_return() throws Exception
    {
        String hello = "hello";

        // MockMvc를 통해 /hello 주소로 http get
        mvc.perform(get("hello"))
                // isOK result = 200 or not
                .andExpect(status().isOk())
                // HelloController result = hello check
                .andExpect(content().string(hello));
    }


    @Test
    public void helloDto_is_return() throws Exception{
        String name = "testName";
        int amount = 1000;

        mvc.perform(
                get("hello/dto")
                // api 테스트를 할 때, 사용할 요청 파라미터를 설정하는 것
                .param("name",name)
                .param("amount", String.valueOf(amount))
                )
                .andExpect(status().isOk())

                // jsonPath = json응답값을 필드별로 검증할 수 있는 메소드
                // $를 기준으로 필드명을 명시한다. 여기서는 name, amount 검증
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }

}
