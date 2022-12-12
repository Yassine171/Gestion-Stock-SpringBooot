package gestion_stock.gestionStockapp.repository;

import gestion_stock.gestionStockapp.dto.CategorieDto;
import gestion_stock.gestionStockapp.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CategorieRepository extends JpaRepository<Categorie,Integer> {

    Categorie findByDesignation(String designation);

    Categorie findByCode(String code);
}
