package io.avalia.fruits.entities;

import io.avalia.fruits.api.model.Article;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

@Entity(name = "cart")
@Getter
@Setter
public class CartEntity implements Serializable {

    @Id
    private int customerId;
    private ArrayList<Article> listArticle;
}
