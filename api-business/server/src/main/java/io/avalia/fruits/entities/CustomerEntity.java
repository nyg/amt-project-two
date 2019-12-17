package io.avalia.fruits.entities;

import lombok.Getter;
import lombok.Setter;
import io.avalia.fruits.api.model.CustomerAddress;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity(name = "customer")
@Getter
@Setter
public class CustomerEntity implements Serializable {

    @Id
    private String email;

    private String firstName;
    private String lastName;
    private BigDecimal price;
    private CustomerAddress address;
}
