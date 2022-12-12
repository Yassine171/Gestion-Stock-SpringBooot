package gestion_stock.gestionStockapp.repository;

import gestion_stock.gestionStockapp.model.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur,Integer> {


    Optional<Fournisseur> findByMail(String mail);
}
