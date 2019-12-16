package io.avalia.fruits.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name = "user")
@Getter
public class UserEntity implements Serializable {

    @Id
    private String email;

    private String firstName;
    private String lastName;
    private String password;
    private boolean active;
    private boolean admin;
}
