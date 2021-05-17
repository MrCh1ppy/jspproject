package com.example.jsp;

import com.example.jsp.manager.toservice.UserManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JspprojectApplicationTests {

    @Autowired
    private UserManager userManager;

    @Test
    void contextLoads() {
        System.out.println("Hello word");
    }


}
