package io.avalia.fruits.repository;

import io.avalia.fruits.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, String> {

}
