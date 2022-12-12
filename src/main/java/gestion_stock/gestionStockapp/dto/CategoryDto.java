package gestion_stock.gestionStockapp.dto;

import gestion_stock.gestionStockapp.model.Article;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;

import java.util.List;

public class CategoryDto {


    private String code;


    private String designation;

    private List<Article> articles;
}
