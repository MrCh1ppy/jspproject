package com.example.jsp.pojo;

import lombok.*;

import java.io.Serializable;

/**
 * @author 橙鼠鼠
 */
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable {
    private String addressString;
    private Integer id;
    private Integer guestId;

    public Address setAddressString (String addressString) {
        this.addressString = addressString;
        return this;
    }

    public Address setId (Integer id) {
        this.id = id;
        return this;
    }

    public Address setGuestId (Integer guestId) {
        this.guestId = guestId;
        return this;
    }
}
