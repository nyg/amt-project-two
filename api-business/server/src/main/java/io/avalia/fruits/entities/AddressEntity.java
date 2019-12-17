package io.avalia.fruits.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity(name = "address")
@Getter
@Setter
public class AddressEntity implements Serializable {

    private String street;
    private String city;
    private String country;
    private int number;
}
