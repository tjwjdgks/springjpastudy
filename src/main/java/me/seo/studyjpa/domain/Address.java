package me.seo.studyjpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

// 가정 : Account에 속한 Value 정보라고 가정 , Address의 생명주기는 Account에 속해 있음
@Embeddable // composite한 value type
@Getter
@Setter
public class Address {
    private String street;

    private String city;

    private String state;

    private String zipCode;
}
