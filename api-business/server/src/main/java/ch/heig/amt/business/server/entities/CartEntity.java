package ch.heig.amt.business.server.entities;

import ch.heig.amt.business.server.api.model.Article;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;

@Entity(name = "cart")
@Getter
@Setter
public class CartEntity implements Serializable {

    @Id
    private String customerId;
    private ArrayList<Article> listArticle;
}
