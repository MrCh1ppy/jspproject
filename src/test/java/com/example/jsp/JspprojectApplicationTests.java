package com.example.jsp;

import com.example.jsp.commons.exception.manager.ProjectException;
import com.example.jsp.manager.toservice.*;
import com.example.jsp.pojo.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@SpringBootTest
class JspprojectApplicationTests {

    @Autowired
    private UserManager userManager;

    @Autowired
    private StoreManager storeManager;

    @Autowired
    private DeliverManager deliverManager;

    @Autowired
    private GuestManager guestManager;

    @Autowired
    private ProductManager productManager;

    @Test
    @Transactional(rollbackFor = Exception.class)
    void contextLoads() {
        User user = new User();
        user.setUsername("cjj").setPassword("2022337");
        try {
            userManager.insert(user);
        } catch (ProjectException e) {
            e.printStackTrace();
        }
        Deliver deliver = new Deliver();
        deliver.setName("d").setTelephone("123").setLoginUser(user);
        try {
            deliverManager.insert(deliver);
            deliverManager.select(deliver.getId());
            deliver.getLoginUser().setUsername("cjj1");
            userManager.restore(user);
            deliver.setTelephone("d1");
            System.out.println(deliver);
            deliverManager.restore(deliver);
            List<Deliver> select = deliverManager.select();
            for (Deliver deliver1 : select) {
                System.out.println(deliver1);
            }
            deliverManager.destroy(deliver);

        } catch (ProjectException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional(rollbackFor = Exception.class)
    void addressGuestTest(){
        Guest guest = new Guest();
        List<Address> addresses = new LinkedList<>();
        guest.setAddresses(addresses);
        User user = new User();
        user.setUsername("user").setPassword("pwd");
        guest.setLoginUser(user).setTelephone("2022337").setName("cjj");
        Address address = new Address();
        address.setAddressString("65432A");
        guest.getAddresses().add(address);
        try {
            userManager.insert(user);
            guestManager.insert(guest);
            Guest select = guestManager.select(guest.getId());
            System.out.println(select);
            select.setTelephone("2022338");
            guestManager.restore(select);
            System.out.println("//////////////////////"+guestManager.select(select.getId()));
            guestManager.destroy(guest);
        } catch (ProjectException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional(rollbackFor = Exception.class)
    public void productTest(){
        Product product = new Product();
        product.setName("蟹皇堡");
        product.setPrice(11.4514);
        Store store = new Store();
        store.setName("蟹堡王");
        store.setTelephone("2022337");
        store.setAddress("under sea");
        User user = new User();
        user.setUsername("cjj");
        user.setPassword("cjj");
        store.setUser(user);
        product.setStore(store);

        try {
            userManager.insert(user);
            storeManager.insert(store);
            productManager.insert(product);
            Product select = productManager.select(product.getId());
            System.out.println(select);
            select.setName("蟹堡皇");
            productManager.restore(select);
            select=productManager.select(select.getId());
            System.out.println(select);
            productManager.destroy(product);
        } catch (ProjectException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void build(){
        User user = new User();
        user.setUsername("userForStore");
        user.setPassword("2022337");
        Store store = new Store();
        store.setUser(user);
        store.setTelephone("StoreTelephone");
        store.setAddress("StoreAddress");
        store.setName("StoreName");
        try {
            userManager.insert(user);
            storeManager.insert(store);
        } catch (ProjectException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void productBuild(){
        Product product = new Product();
        product.setName("productName");
        product.setStore(storeManager.select(18));
        product.setPrice(114.514);
        try {
            productManager.insert(product);
        } catch (ProjectException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void test(){
        Product product = new Product();
        /*推荐的setter调用方法*/
        product.setId(1).setPrice(2.23).setName("cjj");
        System.out.println(product);
    }
}
