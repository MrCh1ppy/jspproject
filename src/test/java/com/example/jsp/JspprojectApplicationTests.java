package com.example.jsp;

import com.example.jsp.manager.toservice.UserManager;
import com.example.jsp.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JspprojectApplicationTests {

    @Autowired
    private UserManager userManager;
    @Test
    void contextLoads() {
        int assertion;
        assertion=0;
        User select = userManager.select(assertion);
        assert select!=null;
        System.out.println("over");
    }


}
