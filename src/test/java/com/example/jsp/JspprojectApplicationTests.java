package com.example.jsp;

import com.example.jsp.commons.exception.manager.ProjectException;
import com.example.jsp.manager.toservice.UserManager;
import com.example.jsp.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class JspprojectApplicationTests {

    @Autowired
    private UserManager userManager;

    @Test
    @Transactional(rollbackFor = Exception.class)
    void contextLoads() {
        User user = new User();
        user.setPassword("cjj");
        user.setUsername("cjj1");
        try {
            Integer insert = userManager.insert(user);
            System.out.println(insert);
            System.out.println(user);
            user.setPassword("cjj2");
            userManager.restore(user);
            System.out.println(user);
            userManager.select(user.getId());
            for (User user1 : userManager.select()) {
                System.out.println(user1);
            }
        } catch (ProjectException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional(rollbackFor = Exception.class)
    void StoreTest(){

    }
}
