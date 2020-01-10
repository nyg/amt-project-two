package ch.heig.amt.business.server.entities;

import lombok.Getter;
import lombok.Setter;
import ch.heig.amt.business.server.api.model.CustomerAddress;

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
