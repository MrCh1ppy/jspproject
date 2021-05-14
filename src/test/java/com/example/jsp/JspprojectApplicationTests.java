package com.example.jsp;

import com.example.jsp.pojo.Store;
import com.example.jsp.pojo.User;
import com.example.jsp.manager.DeliverManager;
import com.example.jsp.manager.StoreManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JspprojectApplicationTests {
    @Autowired
    private DeliverManager deliverManager;
    @Autowired
    private StoreManager service;

    @Test
    void contextLoads() {

        Store store = new Store();
        store.setName("cjj");
        store.setTelephone("2022337");
        store.setAddress("aaaaa");
        User user = new User();
        user.setUsername("cjj");
        user.setPassword("cjj");
        store.setUser(user);
        service.update(store);
    }


}
