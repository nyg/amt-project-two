package ch.heig.amt.business.server.entities;

import ch.heig.amt.business.server.api.model.CustomerAddress;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity(name = "customer")
@Getter
@Setter
public class CustomerEntity implements Serializable {

    @Id
    private String email;

    private String firstName;
    private String lastName;

    @OneToOne
    private AddressEntity address;

    public void setCustomerAddress(CustomerAddress address) {

        if (address == null) {
            return;
        }

        AddressEntity entity = new AddressEntity();
        entity.setStreet(address.getStreet());
        entity.setCity(address.getCity());
        entity.setCountry(address.getCountry());
        entity.setNumber(address.getNumber());
    }
}
