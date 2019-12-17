package io.avalia.fruits.repositories;

import io.avalia.fruits.entities.FruitEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Olivier Liechti on 26/07/17.
 */
public interface CustomerRepository extends CrudRepository<FruitEntity, Long>{

}
