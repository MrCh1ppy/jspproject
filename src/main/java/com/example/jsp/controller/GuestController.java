package com.example.jsp.controller;


import java.util.List;
import com.example.jsp.commons.exception.ProjectException;
import com.example.jsp.commons.model.Transporter;
import com.example.jsp.pojo.Address;
import com.example.jsp.pojo.Guest;
import com.example.jsp.pojo.User;
import com.example.jsp.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GuestController {
    private GuestService guestService;

    @Autowired
    public void setGuestService (GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping("/enroll/{username}/{password}/{name}/{address}/{telephone}")
    public Transporter enroll(@PathVariable("username")String username,
                              @PathVariable("password")String password,
                              @PathVariable("name")String name,
                              @PathVariable("address")String addresses,
                              @PathVariable("telephone")String telephone)throws ProjectException {
        final var user = new User()
                .setUsername(username)
                .setPassword(password);
        List<Address> address= GuestService.apart(addresses);
        final var guest = new Guest()
                .setName(name)
                .setTelephone(telephone)
                .setAddresses(address)
                .setLoginUser(user);
        guestService.enroll(guest,user);
        return new Transporter().setMsg("注册成功");
    }
}

