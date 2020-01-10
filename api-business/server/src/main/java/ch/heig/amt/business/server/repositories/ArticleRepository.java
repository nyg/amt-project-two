package ch.heig.amt.business.server.repositories;

import ch.heig.amt.business.server.entities.ArticleEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Olivier Liechti on 26/07/17.
 */
public interface ArticleRepository extends CrudRepository<ArticleEntity, Long>{

}
