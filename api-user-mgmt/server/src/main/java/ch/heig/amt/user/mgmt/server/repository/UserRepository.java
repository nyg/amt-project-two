package ch.heig.amt.user.mgmt.server.repository;

import ch.heig.amt.user.mgmt.server.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, String> {

}
