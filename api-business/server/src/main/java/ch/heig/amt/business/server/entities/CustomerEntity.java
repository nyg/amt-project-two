package ch.heig.amt.business.server.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
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

    private String address;

}
