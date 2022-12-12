package gestion_stock.gestionStockapp.repository;

import gestion_stock.gestionStockapp.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise,Integer> {


    Entreprise findByNom(String nom);
}
