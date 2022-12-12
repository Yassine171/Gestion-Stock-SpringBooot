package gestion_stock.gestionStockapp.repository;

import gestion_stock.gestionStockapp.model.Vente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;
@Repository
public interface VenteRepository extends JpaRepository<Vente,Integer> {


    Optional<Vente> findByCode(String code);

    Optional<Vente> findByDateVente(Instant dateVente);
}
