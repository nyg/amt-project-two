package ch.heig.amt.business.server.entities;

import ch.heig.amt.business.server.api.model.CustomerAddress;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name = "address")
@Getter
@Setter
public class AddressEntity implements Serializable {
    @Id
    private int id;

    private String street;
    private String city;
    private String country;
    private int number;

    public CustomerAddress asCustomerAddress() {
        return new CustomerAddress().street(street).city(city).country(country).number(number);
    }
}
