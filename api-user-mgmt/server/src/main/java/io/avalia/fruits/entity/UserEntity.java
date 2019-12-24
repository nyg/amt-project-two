package io.avalia.fruits.entity;

import io.avalia.fruits.api.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name = "user")
@Getter
@Setter
public class UserEntity implements Serializable {

    @Id
    private String email;

    private String firstName;
    private String lastName;
    private String password;
    private boolean active;
    private boolean admin;

    public UserEntity() {
        // Nothing
    }

    public UserEntity(User user) {
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.password = user.getPassword();
        this.active = user.getActive();
        this.admin = user.getAdmin();
    }

    public void setFirstNameIfNotNull(String firstName) {
        if (firstName != null) {
            this.firstName = firstName;
        }
    }

    public void setLastNameIfNotNull(String lastName) {
        if (lastName != null) {
            this.lastName = lastName;
        }
    }

    public void setActiveIfNotNull(Boolean active) {
        if (active != null) {
            this.active = active;
        }
    }

    public void setAdminIfNotNull(Boolean admin) {
        if (admin != null) {
            this.admin = admin;
        }
    }
}
